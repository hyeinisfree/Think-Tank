package com.ttteam.thinktank.service;

import com.ttteam.thinktank.controller.SignupRequestDto;
import com.ttteam.thinktank.controller.SignupResponseDto;
import com.ttteam.thinktank.entity.Account;
import com.ttteam.thinktank.exception.DuplicateException;
import com.ttteam.thinktank.repository.AccountRepository;
import com.ttteam.thinktank.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AccountRepository accountRepository;

    // 회원가입
    @Transactional(rollbackFor = Exception.class)
    public SignupResponseDto signup(SignupRequestDto request) {
        checkEmail(request.getEmail());

        Account account = request.toAccount();
        accountRepository.save(account);

        return SignupResponseDto.of(account);
    }

    // 이메일 중복 확인
    @Transactional(readOnly = true)
    public void checkEmail(String email) {
        if (accountRepository.existsByEmail(email)) {
            throw new DuplicateException(ResponseCode.EMAIL_DUPLICATION);
        }
    }
}
