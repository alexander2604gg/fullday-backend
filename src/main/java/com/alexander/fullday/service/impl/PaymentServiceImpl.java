package com.alexander.fullday.service.impl;

import com.alexander.fullday.dto.PaymentRequestDto;
import com.alexander.fullday.entity.Payment;
import com.alexander.fullday.entity.Registration;
import com.alexander.fullday.mapper.PaymentMapper;
import com.alexander.fullday.repository.PaymentRepository;
import com.alexander.fullday.service.PaymentService;
import com.alexander.fullday.service.S3Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl  implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final S3Service s3ImageService;


    @Transactional
    @Override
    public void register(Registration registration, PaymentRequestDto paymentRequestDto, MultipartFile photo) {
        if (paymentRequestDto == null) return;

        Payment payment = PaymentMapper.toEntity(paymentRequestDto);
        payment.setRegistration(registration);
        payment.setPaymentDate(LocalDate.now());
        Payment saved = paymentRepository.save(payment);
        if (photo != null && !photo.isEmpty()) {
            String url = s3ImageService.uploadImage(photo, registration.getDocumentNumber(), saved.getId());
            saved.setReceiptUrl(url);
            paymentRepository.save(saved);
        }
    }


}
