package com.leovegas.wallet.validations;

import com.leovegas.wallet.dto.TransactionDTO;
import com.leovegas.wallet.exceptions.TransactionIdNotUniqueException;
import com.leovegas.wallet.exceptions.ValidationException;
import com.leovegas.wallet.model.Transaction;
import com.leovegas.wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("transactionIdValidation")
public class TransactionIdValidation extends AbstractValidation {

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionIdValidation() throws ValidationException {
    }

    @Override
    public boolean validate(TransactionDTO transactionDTO) throws TransactionIdNotUniqueException {

        Optional<Transaction> transaction = transactionRepository.findById(transactionDTO.getTransactionId());

        if (transaction.isEmpty()){
            return true;
        }
        else
            throw new TransactionIdNotUniqueException("Transaction Id is not unique!");
    }
}
