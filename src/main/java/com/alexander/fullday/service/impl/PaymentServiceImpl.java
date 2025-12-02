package com.alexander.fullday.service.impl;

import com.alexander.fullday.dto.PaymentRequestDto;
import com.alexander.fullday.entity.Payment;
import com.alexander.fullday.entity.Registration;
import com.alexander.fullday.mapper.PaymentMapper;
import com.alexander.fullday.repository.PaymentRepository;
import com.alexander.fullday.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl  implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional
    @Override
    public void register(Registration registration , PaymentRequestDto paymentRequestDto) {
        if (paymentRequestDto == null) return;
        Payment payment = PaymentMapper.toEntity(paymentRequestDto);
        payment.setRegistration(registration);
        paymentRepository.save(payment);
    }

}
