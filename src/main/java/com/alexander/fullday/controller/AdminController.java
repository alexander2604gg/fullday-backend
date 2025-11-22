package com.alexander.fullday.controller;

import com.alexander.fullday.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/participant")
@RequiredArgsConstructor
public class AdminController {

    private final RegistrationService registrationService;

}
