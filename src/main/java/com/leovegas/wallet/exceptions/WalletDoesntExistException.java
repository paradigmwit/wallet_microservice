package com.leovegas.wallet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "The wallet doesn't exist.")
public class WalletDoesntExistException extends RuntimeException  {

    public WalletDoesntExistException(String message) {
        super(message);
    }

    public WalletDoesntExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public WalletDoesntExistException(Throwable cause) {
        super(cause);
    }
}
