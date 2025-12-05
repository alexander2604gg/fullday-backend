package com.alexander.fullday.service;

import com.alexander.fullday.dto.RegistrationRequestDto;
import com.alexander.fullday.dto.RegistrationResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RegistrationService {
    RegistrationResponseDto register(RegistrationRequestDto dto, MultipartFile photo);
    RegistrationResponseDto findByDocument(String documentNumber);
    RegistrationResponseDto verifyEmail(Integer registrationId);
    List<RegistrationResponseDto> findAll ();
}
