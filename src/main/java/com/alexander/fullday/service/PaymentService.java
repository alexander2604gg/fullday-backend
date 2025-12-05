package com.alexander.fullday.service;

import com.alexander.fullday.dto.PaymentRequestDto;
import com.alexander.fullday.entity.Registration;
import org.springframework.web.multipart.MultipartFile;

public interface PaymentService {
    void register(Registration registration , PaymentRequestDto dto, MultipartFile photo);

}
