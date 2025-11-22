package com.alexander.fullday.dto;

import com.alexander.fullday.enums.ParticipantType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationRequestDto(

        @NotBlank
        @Size(max = 20)
        String documentNumber,

        @NotBlank
        @Size(max = 100)
        String fullName,

        @NotBlank
        @Email
        @Size(max = 120)
        String email,

        @NotBlank
        @Size(max = 15)
        String phone,

        ParticipantType type
) {}