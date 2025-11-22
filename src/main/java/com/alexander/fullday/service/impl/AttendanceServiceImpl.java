package com.alexander.fullday.service.impl;

import com.alexander.fullday.dto.AttendanceRequestDto;
import com.alexander.fullday.dto.AttendanceResponseDto;
import com.alexander.fullday.entity.Attendance;
import com.alexander.fullday.entity.Registration;
import com.alexander.fullday.mapper.AttendanceMapper;
import com.alexander.fullday.repository.AttendanceRepository;
import com.alexander.fullday.repository.RegistrationRepository;
import com.alexander.fullday.service.AttendanceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final RegistrationRepository registrationRepository;

    @Override
    @Transactional
    public AttendanceResponseDto checkIn(AttendanceRequestDto dto) {

        Registration reg = registrationRepository.findById(dto.registrationId())
                .orElseThrow(() -> new IllegalArgumentException("Registro no encontrado."));

        // Evita que firme asistencia más de una vez
        if (attendanceRepository.existsByRegistrationId(dto.registrationId())) {
            throw new IllegalStateException("Este participante ya registró asistencia.");
        }

        Attendance att = new Attendance();
        att.setRegistration(reg);

        Attendance saved = attendanceRepository.save(att);

        return AttendanceMapper.toDto(saved);
    }
}