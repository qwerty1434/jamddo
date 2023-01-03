package com.jamddo.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {


    // 400
    NOT_EQUAL_PASSWORD(400, "비밀번호가 서로 일치하지 않습니다"),


    //409 CONFLICT 중복된 리소스
    ALREADY_REGISTERED_MEMBER(409, "이미 가입되어 있는 회원입니다.");

    private final int status;
    private final String message;

    }
