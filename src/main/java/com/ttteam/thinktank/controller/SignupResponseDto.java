package com.ttteam.thinktank.controller;

import com.ttteam.thinktank.entity.Account;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignupResponseDto {

    private String uuid;

    public static SignupResponseDto of(Account account) {
        return SignupResponseDto.builder()
                .uuid(account.getUuid())
                .build();
    }
}
