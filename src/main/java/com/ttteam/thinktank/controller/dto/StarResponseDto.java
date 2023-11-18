package com.ttteam.thinktank.controller.dto;

import com.ttteam.thinktank.entity.Star;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StarResponseDto {
    private final Long starId;
    private final Long accountId;
    private final Integer groupId;
    private final String title;
    private final String content;

    public static StarResponseDto of(Star star){
        return StarResponseDto.builder()
            .starId(star.getId())
            .accountId(star.getAccount().getId())
            .groupId(star.getGroup_id())
            .title(star.getTitle())
            .content(star.getContent())
            .build();
    }
}
