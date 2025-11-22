package com.alexander.fullday.dto;

import jakarta.validation.constraints.NotNull;

public record AttendanceRequestDto(
        @NotNull
        Integer registrationId
) {}