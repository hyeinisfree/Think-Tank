package com.ttteam.thinktank.controller;

import com.ttteam.thinktank.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {

    private String id;
    private String name;
    private String email;
    private String age;
    private String gender;
    private String birthday;

    public Account toAccount() {
        return Account.builder()
                .uuid(UUID.randomUUID().toString())
                .naverId(id)
                .name(name)
                .email(email)
                .age(age)
                .gender(gender)
                .birthday(birthday)
                .build();
    }
}
