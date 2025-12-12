package com.alexander.fullday.service.impl;

import com.alexander.fullday.dto.AttendanceResponseDto;
import com.alexander.fullday.dto.RegistrationRequestDto;
import com.alexander.fullday.dto.RegistrationResponseDto;
import com.alexander.fullday.entity.Attendance;
import com.alexander.fullday.entity.Registration;
import com.alexander.fullday.enums.errors.FullDayErrorEnum;
import com.alexander.fullday.exception.ConflictException;
import com.alexander.fullday.mapper.AttendanceMapper;
import com.alexander.fullday.mapper.RegistrationMapper;
import com.alexander.fullday.repository.AttendanceRepository;
import com.alexander.fullday.repository.RegistrationRepository;
import com.alexander.fullday.service.AttendanceService;
import com.alexander.fullday.service.EmailService;
import com.alexander.fullday.service.PaymentService;
import com.alexander.fullday.service.RegistrationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EmailService emailService;
    private final PaymentService paymentService;
    private final AttendanceRepository attendanceRepository;

    @Transactional
    @Override
    public RegistrationResponseDto register(RegistrationRequestDto dto, MultipartFile photo) {
        validateFields(dto);
        Registration registration = RegistrationMapper.toEntity(dto);
        Registration registrationSaved = registrationRepository.save(registration);
        paymentService.register(registrationSaved,dto.payment(), photo);
        emailService.sendEmail(dto.email(), dto.fullName());
        return RegistrationMapper.toDto(registrationSaved);
    }

    @Transactional
    @Override
    public List<RegistrationResponseDto> findAll() {

        // 1. Obtener todos los registros
        List<Registration> registrations = registrationRepository.findAll();

        // 2. Obtener todas las asistencias
        List<Attendance> attendances = attendanceRepository.findAll();

        // 3. Mapear Attendance por id de Registration
        Map<Integer, Attendance> attendanceMap = attendances.stream()
                .collect(Collectors.toMap(
                        a -> a.getRegistration().getId(),
                        a -> a,
                        (a1, a2) -> a1
                ));

        return registrations.stream()
                .map(reg -> {
                    Attendance attendance = attendanceMap.get(reg.getId());

                    AttendanceResponseDto attendanceDto = attendance != null
                            ? new AttendanceResponseDto(
                            attendance.getId(),
                            attendance.getRegistration().getId(),
                            attendance.getCheckInAt()
                    )
                            : null;

                    return new RegistrationResponseDto(
                            reg.getId(),
                            reg.getDocumentNumber(),
                            reg.getFullName(),
                            reg.getEmail(),
                            reg.getPhone(),
                            reg.getType(),
                            reg.isEmailVerified(),
                            reg.getRegisteredAt(),
                            attendanceDto
                    );
                })
                .collect(Collectors.toList());

    }


    @Transactional
    @Override
    public RegistrationResponseDto findByDocument(String documentNumber) {
        Registration r = registrationRepository
                .findByDocumentNumber(documentNumber)
                .orElseThrow(() -> new IllegalArgumentException("No existe registro con ese documento."));
        return RegistrationMapper.toDto(r);
    }

    @Transactional
    @Override
    public RegistrationResponseDto verifyEmail(Integer registrationId) {
        Registration r = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new IllegalArgumentException("Registro no encontrado."));

        if (!r.isEmailVerified()) {
            r.setEmailVerified(true);
        }

        return RegistrationMapper.toDto(r);
    }

    private void validateFields (RegistrationRequestDto dto) {
        validateEmailNotExists(dto.email());
        validateDocumentNotExists(dto.documentNumber());
        validatePhoneNotExists(dto.phone());
    }

    private void validateEmailNotExists (String email) {
        if (registrationRepository.existsByEmail(email)) {
            throw new ConflictException(FullDayErrorEnum.EMAIL_ALREADY_EXISTS);
        }
    }

    private void validateDocumentNotExists (String documentNumber) {
        if (registrationRepository.existsByDocumentNumber(documentNumber)) {
            throw new ConflictException(FullDayErrorEnum.DOCUMENT_ALREADY_EXISTS);
        }
    }

    private void validatePhoneNotExists (String phone) {
        if (registrationRepository.existsByPhone(phone)) {
            throw new ConflictException(FullDayErrorEnum.PHONE_ALREADY_EXISTS);
        }
    }

}