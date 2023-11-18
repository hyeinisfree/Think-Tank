package com.ttteam.thinktank.service;

import com.ttteam.thinktank.controller.dto.StarRequestDto;
import com.ttteam.thinktank.controller.dto.StarResponseDto;
import com.ttteam.thinktank.entity.Account;
import com.ttteam.thinktank.entity.Star;
import com.ttteam.thinktank.exception.ResourceNotFoundException;
import com.ttteam.thinktank.repository.AccountRepository;
import com.ttteam.thinktank.repository.StarRepository;
import com.ttteam.thinktank.util.ResponseCode;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class StarService {

    private final AccountRepository accountRepository;
    private final StarRepository starRepository;

    public StarResponseDto createStar(Long accountId, StarRequestDto request) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new ResourceNotFoundException(ResponseCode.ACCOUNT_NOT_FOUND));

        LocalDateTime now = LocalDateTime.now();
        Integer groupId = Integer.parseInt(
            Integer.toString(now.getYear()) + Integer.toString(now.getMonthValue()));

        Star star = Star.builder()
            .account(account)
            .title(request.getTitle())
            .content(request.getContent())
            .group_id(groupId)
            .build();

        starRepository.save(star);

        return StarResponseDto.of(star);
    }

    public StarResponseDto readStar(Long starId) {
        Star star = starRepository.findById(starId)
            .orElseThrow(() -> new ResourceNotFoundException(ResponseCode.STAR_NOT_FOUND));
        return StarResponseDto.of(star);
    }
}
