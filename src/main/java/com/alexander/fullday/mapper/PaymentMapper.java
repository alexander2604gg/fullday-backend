package com.alexander.fullday.mapper;

import com.alexander.fullday.dto.PaymentRequestDto;
import com.alexander.fullday.entity.Payment;

public class PaymentMapper {

    public static Payment toEntity(PaymentRequestDto dto) {
         Payment payment = new Payment();
         payment.setPaymentDate(dto.paymentDate());
         payment.setOperationNumber(dto.operationNumber());
         payment.setPaymentMethod(dto.paymentMethod());
         return payment;
    }
}
