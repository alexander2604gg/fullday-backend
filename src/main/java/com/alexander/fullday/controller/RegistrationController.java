package com.alexander.fullday.controller;

import com.alexander.fullday.dto.RegistrationRequestDto;
import com.alexander.fullday.dto.RegistrationResponseDto;
import com.alexander.fullday.service.RegistrationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/admin/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RegistrationResponseDto> register(
            @RequestPart("data") String requestJson,
            @RequestPart(value = "photo", required = false) MultipartFile photo
    ) throws JsonProcessingException {

        RegistrationRequestDto request = new ObjectMapper().readValue(requestJson, RegistrationRequestDto.class);

        RegistrationResponseDto response = registrationService.register(request, photo);
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<List<RegistrationResponseDto>> findAll () {
        return ResponseEntity.ok(registrationService.findAll());
    }

}
