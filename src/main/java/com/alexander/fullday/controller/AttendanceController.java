package com.alexander.fullday.controller;

import com.alexander.fullday.dto.AttendanceRequestDto;
import com.alexander.fullday.dto.AttendanceResponseDto;
import com.alexander.fullday.dto.RegistrationRequestDto;
import com.alexander.fullday.dto.RegistrationResponseDto;
import com.alexander.fullday.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<AttendanceResponseDto> register(@RequestBody AttendanceRequestDto attendanceRequestDto) {
        return ResponseEntity.ok(attendanceService.checkIn(attendanceRequestDto));
    }

}
