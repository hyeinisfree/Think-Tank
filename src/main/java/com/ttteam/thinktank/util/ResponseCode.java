package com.ttteam.thinktank.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    /* 200 OK : 요청 성공 */
    SIGNIN_SUCCESS(OK, "로그인 성공"),
    SIGNOUT_SUCCESS(OK, "로그아웃 성공"),

    /* 201 CREATED : 요청 성공, 자원 생성 */
    SIGNUP_SUCCESS(CREATED, "회원가입 성공"),
    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
