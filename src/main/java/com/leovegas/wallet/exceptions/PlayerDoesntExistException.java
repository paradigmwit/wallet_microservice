package com.leovegas.wallet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "The player doesn't exist.")
public class PlayerDoesntExistException extends RuntimeException  {

    public PlayerDoesntExistException(String message) {
        super(message);
    }

    public PlayerDoesntExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayerDoesntExistException(Throwable cause) {
        super(cause);
    }
}
