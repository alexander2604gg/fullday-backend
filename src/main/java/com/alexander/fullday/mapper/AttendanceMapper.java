package com.alexander.fullday.mapper;


import com.alexander.fullday.dto.AttendanceResponseDto;
import com.alexander.fullday.entity.Attendance;

public class AttendanceMapper {

    public static AttendanceResponseDto toDto(Attendance entity) {
        return new AttendanceResponseDto(
                entity.getId(),
                entity.getRegistration().getId(),
                entity.getCheckInAt()
        );
    }
}