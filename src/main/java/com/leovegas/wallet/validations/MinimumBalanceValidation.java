package com.leovegas.wallet.validations;

import com.leovegas.wallet.dto.TransactionDTO;
import com.leovegas.wallet.exceptions.MinimumBalanceException;
import com.leovegas.wallet.exceptions.ValidationException;
import com.leovegas.wallet.model.Player;
import com.leovegas.wallet.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("minimumBalanceValidation")
public class MinimumBalanceValidation extends AbstractValidation {

    @Autowired
    private PlayerRepository playerRepository;

    public MinimumBalanceValidation() throws ValidationException {
    }

    @Override
    public boolean validate(TransactionDTO transactionDTO) throws MinimumBalanceException {

        Player player = playerRepository.findByPlayerNameIgnoreCase(transactionDTO.getPlayerName());

        if (player.getWallet().getBalance() >= Double.parseDouble(transactionDTO.getTransactionAmount())){
            return true;
        }
        else
            throw new MinimumBalanceException("Not enough balance!");
    }
}
