package com.jamddo.global.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

}
