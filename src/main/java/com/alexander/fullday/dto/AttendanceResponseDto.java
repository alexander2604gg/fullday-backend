package com.alexander.fullday.dto;

import java.time.LocalDateTime;

public record AttendanceResponseDto(
        Long id,
        Integer registrationId,
        LocalDateTime checkInAt
) {}