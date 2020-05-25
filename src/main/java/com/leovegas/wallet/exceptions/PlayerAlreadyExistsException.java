package com.leovegas.wallet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason = "The player already exists.")
public class PlayerAlreadyExistsException extends RuntimeException  {

    public PlayerAlreadyExistsException(String message) {
        super(message);
    }

    public PlayerAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayerAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
