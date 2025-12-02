package com.alexander.fullday.dto;

import com.alexander.fullday.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PaymentRequestDto(
        @NotNull LocalDate paymentDate,
        @NotBlank PaymentMethod paymentMethod,
        @NotBlank String operationNumber
) {}

