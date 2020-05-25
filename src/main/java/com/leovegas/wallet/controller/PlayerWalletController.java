package com.leovegas.wallet.controller;

import com.leovegas.wallet.dto.TransactionDTO;
import com.leovegas.wallet.dto.WalletDTO;
import com.leovegas.wallet.exceptions.PlayerDoesntExistException;
import com.leovegas.wallet.exceptions.ValidationException;
import com.leovegas.wallet.exceptions.WalletDoesntExistException;
import com.leovegas.wallet.service.TransactionService;
import com.leovegas.wallet.service.ValidatorService;
import com.leovegas.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PlayerWalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ValidatorService validatorService;

    @GetMapping(value = "{playerName}/wallet", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WalletDTO> balance(@PathVariable ("playerName") final String playerName) throws PlayerDoesntExistException, WalletDoesntExistException {

        return ResponseEntity.ok(walletService.getWallet(playerName));
    }


    @PostMapping(value = "/{playerName}/wallet/credit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WalletDTO> performCredit(@PathVariable ("playerName") final String playerName,
                                   @RequestBody final TransactionDTO transactionDTO) throws ValidationException {

        validatorService.performValidation(ValidatorService.creditValidator, transactionDTO);

        WalletDTO walletDTO = walletService.credit(transactionDTO);

        return ResponseEntity.ok(walletDTO);
    }


    @PostMapping(value = "/{playerName}/wallet/debit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WalletDTO> performDebit(@PathVariable ("playerName") final String playerName,
                                  @RequestBody final TransactionDTO transactionDTO) throws ValidationException {

        validatorService.performValidation(ValidatorService.debitValidator, transactionDTO);

        WalletDTO walletDTO = walletService.debit(transactionDTO);

        return ResponseEntity.ok(walletDTO);
    }


    @GetMapping(value = "/{playerName}/wallet/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TransactionDTO> history(@PathVariable ("playerName") final String playerName) throws ValidationException {

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setPlayerName(playerName);

        validatorService.performValidation(ValidatorService.playerValidator, transactionDTO);

        return transactionService.getTransactions(playerName);
    }


}
