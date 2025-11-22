package com.alexander.fullday.service.impl;

import com.alexander.fullday.dto.RegistrationRequestDto;
import com.alexander.fullday.dto.RegistrationResponseDto;
import com.alexander.fullday.entity.Registration;
import com.alexander.fullday.mapper.RegistrationMapper;
import com.alexander.fullday.repository.RegistrationRepository;
import com.alexander.fullday.service.RegistrationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;

    @Override
    @Transactional
    public RegistrationResponseDto register(RegistrationRequestDto dto) {

        if (registrationRepository.existsByDocumentNumber(dto.documentNumber())) {
            throw new IllegalArgumentException("El documento ya está registrado.");
        }

        if (registrationRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("El correo ya está registrado.");
        }

        Registration entity = RegistrationMapper.toEntity(dto);
        Registration saved = registrationRepository.save(entity);

        return RegistrationMapper.toDto(saved);
    }

    @Override
    @Transactional
    public RegistrationResponseDto findByDocument(String documentNumber) {
        Registration r = registrationRepository
                .findByDocumentNumber(documentNumber)
                .orElseThrow(() -> new IllegalArgumentException("No existe registro con ese documento."));
        return RegistrationMapper.toDto(r);
    }

    @Override
    @Transactional
    public RegistrationResponseDto verifyEmail(Integer registrationId) {
        Registration r = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new IllegalArgumentException("Registro no encontrado."));

        if (!r.isEmailVerified()) {
            r.setEmailVerified(true);
        }

        return RegistrationMapper.toDto(r);
    }
}