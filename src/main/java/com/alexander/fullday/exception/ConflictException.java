package com.alexander.fullday.exception;

import com.alexander.fullday.enums.errors.FullDayErrorEnum;

public class ConflictException extends RuntimeException {
    public ConflictException(FullDayErrorEnum errorEnum) {
        super(errorEnum.message());
    }
}
