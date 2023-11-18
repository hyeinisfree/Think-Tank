package com.ttteam.thinktank.controller;

import com.ttteam.thinktank.service.AuthService;
import com.ttteam.thinktank.util.ResponseCode;
import com.ttteam.thinktank.util.ResponseData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("signup")
    public ResponseEntity<ResponseData<SignupResponseDto>> signup(
            @Valid @RequestBody SignupRequestDto request) {
        SignupResponseDto data = authService.signup(request);
        return ResponseData.toResponseEntity(ResponseCode.SIGNUP_SUCCESS, data);
    }

}
