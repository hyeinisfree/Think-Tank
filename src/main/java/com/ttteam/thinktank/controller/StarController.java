package com.ttteam.thinktank.controller;

import com.ttteam.thinktank.controller.dto.StarRequestDto;
import com.ttteam.thinktank.controller.dto.StarResponseDto;
import com.ttteam.thinktank.service.StarService;
import com.ttteam.thinktank.util.ResponseCode;
import com.ttteam.thinktank.util.ResponseData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.FetchProfiles;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("stars")
public class StarController {

    private final StarService starService;

    @PostMapping("")
    public ResponseEntity<ResponseData<StarResponseDto>> createStar(
        @PathVariable @NotNull String uuid, @Valid StarRequestDto request) {
        StarResponseDto data = starService.createStar(uuid, request);
        return ResponseData.toResponseEntity(ResponseCode.STAR_CREATE_SUCCESS, data);
    }

    @PatchMapping("")
    public ResponseEntity<ResponseData> updateStar(
        @PathVariable @NotNull String uuid, @PathVariable @NotNull Long starId, @Valid StarRequestDto request) {
        starService.updateStar(uuid, starId, request);
        return ResponseData.toResponseEntity(ResponseCode.STAR_UPDATE_SUCCESS);
    }

    @GetMapping("")
    public ResponseEntity<ResponseData<StarResponseDto>> readStar(
        @PathVariable @NotNull String uuid, @PathVariable @NotNull Long starId) {
        StarResponseDto data = starService.readStar(starId);
        return ResponseData.toResponseEntity(ResponseCode.STAR_READ_SUCCESS, data);
    }
}
