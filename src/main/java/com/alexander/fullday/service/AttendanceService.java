package com.alexander.fullday.service;

import com.alexander.fullday.dto.AttendanceRequestDto;
import com.alexander.fullday.dto.AttendanceResponseDto;

public interface AttendanceService {
    AttendanceResponseDto checkIn(AttendanceRequestDto dto);
}