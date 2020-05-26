package com.leovegas.wallet.service;

import com.leovegas.wallet.dto.TransactionDTO;
import com.leovegas.wallet.dto.WalletDTO;
import com.leovegas.wallet.exceptions.PlayerDoesntExistException;
import com.leovegas.wallet.exceptions.WalletDoesntExistException;
import com.leovegas.wallet.model.Player;
import com.leovegas.wallet.model.Transaction;
import com.leovegas.wallet.model.Wallet;
import com.leovegas.wallet.repository.PlayerRepository;
import com.leovegas.wallet.repository.TransactionRepository;
import com.leovegas.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    public WalletDTO getWallet(String playerName) throws PlayerDoesntExistException, WalletDoesntExistException {
        Optional<Player> player = Optional.ofNullable(playerRepository.findByPlayerNameIgnoreCase(playerName));

        if(player.isEmpty())
            throw new PlayerDoesntExistException("The player does not exist!");

        Optional<Wallet> wallet = Optional.ofNullable(walletRepository.findByPlayer(player.get()));
        if(wallet.isEmpty())
            throw new WalletDoesntExistException("The wallet does not exist!");

        return  mapWalletDTO(wallet.get());
    }


    @Transactional
    public WalletDTO credit(TransactionDTO transactionDTO) {

        Player player = playerRepository.findByPlayerNameIgnoreCase(transactionDTO.getPlayerName());

        Wallet wallet = player.getWallet();
        wallet.setBalance(wallet.getBalance() + Double.parseDouble(transactionDTO.getTransactionAmount()));
        wallet = walletRepository.save(wallet);

        transactionDTO.setTransactionType("CREDIT");
        createTransactionHistory(transactionDTO, player);

        return mapWalletDTO(wallet);
    }


    @Transactional()
    public WalletDTO debit(TransactionDTO transactionDTO) {

        Player player = playerRepository.findByPlayerNameIgnoreCase(transactionDTO.getPlayerName());

        Wallet wallet = player.getWallet();
        wallet.setBalance(wallet.getBalance() - Double.parseDouble(transactionDTO.getTransactionAmount()));
        wallet = walletRepository.save(wallet);

        transactionDTO.setTransactionType("DEBIT");
        createTransactionHistory(transactionDTO, player);

        return mapWalletDTO(wallet);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    private void createTransactionHistory(TransactionDTO transactionDTO, Player player) {

        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionDTO.getTransactionId());
        transaction.setAmount(Long.parseLong(transactionDTO.getTransactionAmount()));
        transaction.setCurrencyCode(player.getWallet().getCurrencyCode());
        transaction.setTransactionDate(new Date(System.currentTimeMillis()));
        transaction.setTransactionType(transactionDTO.getTransactionType());
        transaction = transactionRepository.save(transaction);
        transaction.setPlayer(player);
        transactionRepository.save(transaction);
    }

    private WalletDTO mapWalletDTO(Wallet wallet) {
        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setBalance(wallet.getBalance());
        walletDTO.setCurrencyCode(wallet.getCurrencyCode());
        return walletDTO;
    }

}
