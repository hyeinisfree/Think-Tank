package com.ttteam.thinktank.service;

import com.ttteam.thinktank.controller.dto.StarRequestDto;
import com.ttteam.thinktank.controller.dto.StarResponseDto;
import com.ttteam.thinktank.entity.Account;
import com.ttteam.thinktank.entity.Star;
import com.ttteam.thinktank.exception.ForbiddenException;
import com.ttteam.thinktank.exception.ResourceNotFoundException;
import com.ttteam.thinktank.repository.AccountRepository;
import com.ttteam.thinktank.repository.StarRepository;
import com.ttteam.thinktank.util.ResponseCode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class StarService {

    private final AccountRepository accountRepository;
    private final StarRepository starRepository;

    public StarResponseDto createStar(String uuid, StarRequestDto request) {
        Account account = accountRepository.findByUuid(uuid)
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

    public StarResponseDto readStar(String uuid, Long starId) {
        Account account = accountRepository.findByUuid(uuid)
            .orElseThrow(() -> new ResourceNotFoundException(ResponseCode.ACCOUNT_NOT_FOUND));

        Star star = starRepository.findById(starId)
            .orElseThrow(() -> new ResourceNotFoundException(ResponseCode.STAR_NOT_FOUND));

        if (!account.equals(star.getAccount())) {
            throw new ForbiddenException(ResponseCode.STAR_READ_FAIL_NOT_OWNER);
        }

        return StarResponseDto.of(star);
    }

    public List<StarResponseDto> readMyStarsMonthly(String uuid, String range) {
        Account account = accountRepository.findByUuid(uuid)
            .orElseThrow(() -> new ResourceNotFoundException(ResponseCode.ACCOUNT_NOT_FOUND));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        LocalDateTime month = LocalDateTime.parse(range, formatter);
        YearMonth targetYearMonth = YearMonth.from(month);
        LocalDate firstDate = targetYearMonth.atDay(1);
        LocalDate lastDate = targetYearMonth.atEndOfMonth();

        List<StarResponseDto> data = starRepository.findAllByAccountAndCreatedAtBetween(account,
                firstDate, lastDate).stream().map(s -> StarResponseDto.of(s))
            .collect(Collectors.toList());

        if(!data.isEmpty()){
            if(!data.getFirst().getAccountId().equals(account.getId())){
                throw new ForbiddenException(ResponseCode.STAR_READ_FAIL_NOT_OWNER);
            }
        }

        return data;
    }

    public void updateStar(String uuid, Long starId, StarRequestDto request) {
        Account account = accountRepository.findByUuid(uuid)
            .orElseThrow(() -> new ResourceNotFoundException(ResponseCode.ACCOUNT_NOT_FOUND));

        Star star = starRepository.findById(starId)
            .orElseThrow(() -> new ResourceNotFoundException(ResponseCode.STAR_NOT_FOUND));

        if (!account.equals(star.getAccount())) {
            throw new ForbiddenException(ResponseCode.STAR_UPDATE_FAIL_NOT_OWNER);
        }

        star.update(request.getTitle(), request.getContent());
    }

    public void deleteStar(String uuid, Long starId) {
        Account account = accountRepository.findByUuid(uuid)
            .orElseThrow(() -> new ResourceNotFoundException(ResponseCode.ACCOUNT_NOT_FOUND));

        Star star = starRepository.findById(starId)
            .orElseThrow(() -> new ResourceNotFoundException(ResponseCode.STAR_NOT_FOUND));

        if (!account.equals(star.getAccount())) {
            throw new ForbiddenException(ResponseCode.STAR_DELETE_FAIL_NOT_OWNER);
        }

        starRepository.delete(star);
    }

}
