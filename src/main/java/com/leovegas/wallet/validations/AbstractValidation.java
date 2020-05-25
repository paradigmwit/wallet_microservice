package com.leovegas.wallet.validations;

import com.leovegas.wallet.dto.TransactionDTO;
import com.leovegas.wallet.exceptions.ValidationException;

public abstract class AbstractValidation {

    public abstract boolean validate(TransactionDTO transactionDTO) throws ValidationException;
}
