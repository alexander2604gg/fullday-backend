package com.alexander.fullday.service.impl;

import com.alexander.fullday.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class SesEmailService implements EmailService {

    private final SesClient sesClient;

    @Async
    @Override
    public void sendEmail(String to, String nombre) {

        String html = obtainBody().replace("${nombre}", nombre);
        Destination destination = Destination.builder()
                .toAddresses(to)
                .build();
        Content content = Content.builder()
                .data(html)
                .build();
        Message message = Message.builder()
                .subject(Content.builder().data("Confirmación de registro para Full Day!").build())
                .body(Body.builder().html(content).build())
                .build();
        SendEmailRequest request = SendEmailRequest.builder()
                .source("no-reply@fullday.lat")
                .destination(destination)
                .message(message)
                .build();
        sesClient.sendEmail(request);
    }


    public String obtainBody() {
        try {
            ClassPathResource resource = new ClassPathResource("templates/fullday_success.html");
            return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
