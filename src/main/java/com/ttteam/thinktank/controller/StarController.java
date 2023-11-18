package com.ttteam.thinktank.controller;

import com.ttteam.thinktank.controller.dto.StarRequestDto;
import com.ttteam.thinktank.controller.dto.StarResponseDto;
import com.ttteam.thinktank.service.StarService;
import com.ttteam.thinktank.util.ResponseCode;
import com.ttteam.thinktank.util.ResponseData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.FetchProfiles;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("stars")
public class StarController {

    private final StarService starService;

    @PostMapping("")
    public ResponseEntity<ResponseData<StarResponseDto>> createStar(
        @RequestParam @NotNull String uuid, @Valid StarRequestDto request) {
        StarResponseDto data = starService.createStar(uuid, request);
        return ResponseData.toResponseEntity(ResponseCode.STAR_CREATE_SUCCESS, data);
    }

    @PatchMapping("{starId}")
    public ResponseEntity<ResponseData> updateStar(
        @RequestParam @NotNull String uuid, @PathVariable @NotNull Long starId,
        @Valid StarRequestDto request) {
        starService.updateStar(uuid, starId, request);
        return ResponseData.toResponseEntity(ResponseCode.STAR_UPDATE_SUCCESS);
    }

    @GetMapping("{starId}")
    public ResponseEntity<ResponseData<StarResponseDto>> readStar(
        @RequestParam @NotNull String uuid, @PathVariable @NotNull Long starId) {
        StarResponseDto data = starService.readStar(uuid, starId);
        return ResponseData.toResponseEntity(ResponseCode.STAR_READ_SUCCESS, data);
    }

    @GetMapping("")
    public ResponseEntity<ResponseData<List<StarResponseDto>>> readMyStarsMonthly(
        @RequestParam @NotNull String uuid, @RequestParam @NotNull String range) {
        List<StarResponseDto> data = starService.readMyStarsMonthly(uuid, range);
        return ResponseData.toResponseEntity(ResponseCode.STAR_READ_SUCCESS, data);
    }

    @DeleteMapping("{starId}")
    public ResponseEntity<ResponseData> deleteStar(@RequestParam @NotNull String uuid,
        @PathVariable @NotNull Long starId) {
        starService.deleteStar(uuid, starId);
        return ResponseData.toResponseEntity(ResponseCode.STAR_DELETE_SUCCESS);
    }
}
