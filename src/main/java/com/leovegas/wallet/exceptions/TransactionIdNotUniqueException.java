package com.leovegas.wallet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason = "The transaction has already been completed.")
public class TransactionIdNotUniqueException extends RuntimeException  {

    public TransactionIdNotUniqueException(String message) {
        super(message);
    }

    public TransactionIdNotUniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionIdNotUniqueException(Throwable cause) {
        super(cause);
    }
}
