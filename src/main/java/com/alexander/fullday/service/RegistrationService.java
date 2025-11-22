package com.alexander.fullday.service;

import com.alexander.fullday.dto.RegistrationRequestDto;
import com.alexander.fullday.dto.RegistrationResponseDto;

public interface RegistrationService {

    RegistrationResponseDto register(RegistrationRequestDto dto);
    RegistrationResponseDto findByDocument(String documentNumber);
    RegistrationResponseDto verifyEmail(Integer registrationId);

}
