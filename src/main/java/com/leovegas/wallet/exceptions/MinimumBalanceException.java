package com.leovegas.wallet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason = "The balance is not sufficient for this transaction!")
public class MinimumBalanceException extends RuntimeException  {

    public MinimumBalanceException(String message) {
        super(message);
    }

    public MinimumBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MinimumBalanceException(Throwable cause) {
        super(cause);
    }
}
