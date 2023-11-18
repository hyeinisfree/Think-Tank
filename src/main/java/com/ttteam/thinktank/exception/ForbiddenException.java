package com.ttteam.thinktank.exception;

import com.ttteam.thinktank.util.ResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ForbiddenException extends RuntimeException {

    private final ResponseCode responseCode;
}
