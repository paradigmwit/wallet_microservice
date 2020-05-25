package com.leovegas.wallet.service;

import com.leovegas.wallet.dto.TransactionDTO;
import com.leovegas.wallet.model.Player;
import com.leovegas.wallet.model.Transaction;
import com.leovegas.wallet.repository.PlayerRepository;
import com.leovegas.wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PlayerRepository playerRepository;


    public List<TransactionDTO> getTransactions(String playerName){

        Player player = playerRepository.findByPlayerNameIgnoreCase(playerName);

        List<Transaction> transactionList = transactionRepository.findByPlayer(player);

        return transactionList.stream()
                .map(this::mapTransactionDTO)
                .collect(Collectors.toList());
    }


    private TransactionDTO mapTransactionDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionId(transaction.getTransactionId());
        transactionDTO.setPlayerName(transaction.getPlayer().getPlayerName());
        transactionDTO.setTransactionAmount(transaction.getCurrencyCode() + " " + transaction.getAmount());
        transactionDTO.setTransactionType(transaction.getTransactionType());
        transactionDTO.setTransactionDate(transaction.getTransactionDate());
        return transactionDTO;
    }
}
