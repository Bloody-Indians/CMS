package com.bawag.cms.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CmsException extends RuntimeException {
    public CmsException(String message) {

        super(message);
    }

    public CmsException(String message, Throwable cause) {
        super(message, cause);
    }

}
