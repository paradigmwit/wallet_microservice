package com.leovegas.wallet.service;

import com.leovegas.wallet.dto.TransactionDTO;
import com.leovegas.wallet.exceptions.ValidationException;
import com.leovegas.wallet.validations.AbstractValidation;
import com.leovegas.wallet.validations.MinimumBalanceValidation;
import com.leovegas.wallet.validations.PlayerValidation;
import com.leovegas.wallet.validations.TransactionIdValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValidatorService {

    @Autowired
    private List<AbstractValidation> validators;

    public static final List<Object> playerValidator = List.of(
            PlayerValidation.class);

    public static final List<Object> creditValidator = List.of(
            PlayerValidation.class,
            TransactionIdValidation.class);

    public static final List<Object> debitValidator = List.of(
            PlayerValidation.class,
            TransactionIdValidation.class,
            MinimumBalanceValidation.class);


    public void performValidation(List<Object> validationName,
                                  TransactionDTO transactionDTO) throws ValidationException {

        List<AbstractValidation> validatorList = validators.stream()
                .filter(v -> validationName.contains(v.getClass()))
                .collect(Collectors.toList());

        for(AbstractValidation validation : validatorList){
            validation.validate(transactionDTO);
        }
    }
}
