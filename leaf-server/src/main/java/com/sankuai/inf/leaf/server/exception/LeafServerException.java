package com.sankuai.inf.leaf.server.exception;

import gaozhi.online.base.exception.BusinessRuntimeException;
import gaozhi.online.base.exception.enums.ServerExceptionEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
public class LeafServerException extends BusinessRuntimeException {
    public LeafServerException(String msg) {
        super(ServerExceptionEnum.GENERAL_ERROR,msg);
    }
}
