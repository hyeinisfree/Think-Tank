package com.ttteam.thinktank.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    /* 200 OK : 요청 성공 */
    SIGNIN_SUCCESS(OK, "로그인 성공"),
    SIGNOUT_SUCCESS(OK, "로그아웃 성공"),
    STAR_READ_SUCCESS(OK, "스타 상세 조회 성공"),

    /* 201 CREATED : 요청 성공, 자원 생성 */
    SIGNUP_SUCCESS(CREATED, "회원가입 성공"),
    STAR_CREATE_SUCCESS(CREATED, "스타 생성 성공"),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    ACCOUNT_NOT_FOUND(NOT_FOUND, "계정 정보를 찾을 수 없습니다"),
    STAR_NOT_FOUND(NOT_FOUND, "스타 정보를 찾을 수 없습니다")
    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
