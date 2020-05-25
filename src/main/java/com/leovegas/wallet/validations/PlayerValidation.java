package com.leovegas.wallet.validations;

import com.leovegas.wallet.dto.TransactionDTO;
import com.leovegas.wallet.exceptions.PlayerDoesntExistException;
import com.leovegas.wallet.exceptions.ValidationException;
import com.leovegas.wallet.model.Player;
import com.leovegas.wallet.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("playerValidation")
public class PlayerValidation extends AbstractValidation {

    @Autowired
    private PlayerRepository playerRepository;

    public PlayerValidation() throws ValidationException {}

    @Override
    public boolean validate(TransactionDTO transactionDTO) throws PlayerDoesntExistException {

        Player player = playerRepository.findByPlayerNameIgnoreCase(transactionDTO.getPlayerName());

        Optional<Player> optionalPlayer = Optional.ofNullable(player);

        if (optionalPlayer.isPresent()){
            return true;
        }
        else
            throw new PlayerDoesntExistException("Player is not present in the system!");
    }
}
