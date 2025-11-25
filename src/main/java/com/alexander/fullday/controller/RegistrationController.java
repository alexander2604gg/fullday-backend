package com.alexander.fullday.controller;

import com.alexander.fullday.dto.RegistrationRequestDto;
import com.alexander.fullday.dto.RegistrationResponseDto;
import com.alexander.fullday.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<RegistrationResponseDto> register(@RequestBody RegistrationRequestDto request) {
        RegistrationResponseDto response = registrationService.register(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<RegistrationResponseDto>> findAll () {
        return ResponseEntity.ok(registrationService.findAll());
    }

}
