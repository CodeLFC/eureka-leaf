package com.sankuai.inf.leaf.server.exception;

import gaozhi.online.base.exception.BusinessRuntimeException;
import gaozhi.online.base.exception.enums.ServerExceptionEnum;
import gaozhi.online.base.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Key is none")
public class NoKeyException extends BusinessRuntimeException {
    public NoKeyException() {
        super(ServerExceptionEnum.GENERAL_ERROR);
    }
}
