package com.jamddo.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {


    // 400
    NOT_VALID_LOTTO_NUM(400,"1~45사이의 숫자만 로또번호로 가능합니다.");

    private final int status;
    private final String message;

    }
