package com.alexander.fullday.service.impl;

import com.alexander.fullday.dto.RegistrationRequestDto;
import com.alexander.fullday.dto.RegistrationResponseDto;
import com.alexander.fullday.entity.Registration;
import com.alexander.fullday.enums.errors.FullDayErrorEnum;
import com.alexander.fullday.exception.ConflictException;
import com.alexander.fullday.mapper.RegistrationMapper;
import com.alexander.fullday.repository.RegistrationRepository;
import com.alexander.fullday.service.EmailService;
import com.alexander.fullday.service.PaymentService;
import com.alexander.fullday.service.RegistrationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EmailService emailService;
    private final PaymentService paymentService;

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
    public List<RegistrationResponseDto> findAll () {
        List<Registration> registrationList = registrationRepository.findAll();
        return RegistrationMapper.toDtoList(registrationList);
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