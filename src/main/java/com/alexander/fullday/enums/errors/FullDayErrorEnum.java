package com.alexander.fullday.enums.errors;

import org.springframework.http.HttpStatus;

public enum FullDayErrorEnum {

    ALREADY_CHECKED_IN("E001", "Este participante ya registró asistencia.", HttpStatus.BAD_REQUEST),
    REGISTRATION_NOT_FOUND("E002", "Registro no encontrado.", HttpStatus.NOT_FOUND),
    DOCUMENT_ALREADY_EXISTS ("E002", "El documento ya ha sido registrado previamente", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS ("E003", "El correo ya ha sido registrado previamente", HttpStatus.BAD_REQUEST),
    PHONE_ALREADY_EXISTS ("E003", "El numero de celular ya ha sido registrado previamente", HttpStatus.BAD_REQUEST);



    private final String code;
    private final String message;
    private final HttpStatus status;

    FullDayErrorEnum(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public String code() { return code; }
    public String message() { return message; }
    public HttpStatus status() { return status; }

}

