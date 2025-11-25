package com.alexander.fullday.mapper;

import com.alexander.fullday.dto.RegistrationRequestDto;
import com.alexander.fullday.dto.RegistrationResponseDto;
import com.alexander.fullday.entity.Registration;

import java.util.List;

public class RegistrationMapper {

    public static Registration toEntity(RegistrationRequestDto dto) {
        Registration r = new Registration();
        r.setDocumentNumber(dto.documentNumber());
        r.setFullName(dto.fullName());
        r.setEmail(dto.email());
        r.setPhone(dto.phone());
        r.setType(dto.type());
        return r;
    }

    public static RegistrationResponseDto toDto(Registration entity) {
        return new RegistrationResponseDto(
                entity.getId(),
                entity.getDocumentNumber(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getType(),
                entity.isEmailVerified(),
                entity.getRegisteredAt()
        );
    }

    public static List<RegistrationResponseDto> toDtoList (List<Registration> registrationList) {
        return registrationList.stream().map(RegistrationMapper::toDto).toList();
    }

}