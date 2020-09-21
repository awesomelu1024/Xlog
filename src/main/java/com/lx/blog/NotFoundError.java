package com.lx.blog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: Lux
 * @Date 2020-06-13 11:56
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundError extends RuntimeException{

    public NotFoundError() {
        super();
    }

    public NotFoundError(String message) {
        super(message);
    }

    public NotFoundError(String message, Throwable cause) {
        super(message, cause);
    }
}
