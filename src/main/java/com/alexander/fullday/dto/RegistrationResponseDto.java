package com.alexander.fullday.dto;

import com.alexander.fullday.enums.ParticipantType;

import java.time.LocalDateTime;

public record RegistrationResponseDto(
        Integer id,
        String documentNumber,
        String fullName,
        String email,
        String phone,
        ParticipantType type,
        boolean emailVerified,
        LocalDateTime registeredAt
) {}