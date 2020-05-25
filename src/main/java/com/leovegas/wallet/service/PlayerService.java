package com.leovegas.wallet.service;

import com.leovegas.wallet.dto.PlayerDTO;
import com.leovegas.wallet.dto.WalletDTO;
import com.leovegas.wallet.exceptions.PlayerAlreadyExistsException;
import com.leovegas.wallet.exceptions.PlayerDoesntExistException;
import com.leovegas.wallet.model.Player;
import com.leovegas.wallet.model.Wallet;
import com.leovegas.wallet.repository.PlayerRepository;
import com.leovegas.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private WalletRepository walletRepository;


    public PlayerDTO getPlayerByPlayerName(String playerName) throws PlayerDoesntExistException {

        Player player = playerRepository.findByPlayerNameIgnoreCase(playerName);

        if(player == null)
            throw new PlayerDoesntExistException("The player does not exist!");

        return mapPlayerDTO(player);
    }

    public List<PlayerDTO> getAllPlayers() {

        List<Player> playerList = playerRepository.findAll();

        return playerList.stream().map(this::mapPlayerDTO).collect(Collectors.toList());
    }


    @Transactional
    public Optional<Long> createPlayer(PlayerDTO playerDTO) throws PlayerAlreadyExistsException {

        Player existingPlayer = playerRepository.findByPlayerNameIgnoreCase(playerDTO.getPlayerName());
        if(existingPlayer!=null && existingPlayer.getPlayerId() != null)
            throw new PlayerAlreadyExistsException("Player already exists.");

        Wallet wallet = new Wallet();
        wallet.setBalance(playerDTO.getWalletDTO().getBalance());
        wallet.setCurrencyCode(playerDTO.getWalletDTO().getCurrencyCode());
        walletRepository.save(wallet);

        Player player = new Player();
        player.setPlayerName(playerDTO.getPlayerName());
        player.setWallet(wallet);
        player.setCreated(new Date( System.currentTimeMillis()));
        player = playerRepository.save(player);
        return Optional.ofNullable(player.getPlayerId());
    }


    private PlayerDTO mapPlayerDTO(Player p) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerName(p.getPlayerName());
        playerDTO.setCreated(p.getCreated());

        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setBalance(p.getWallet().getBalance());
        walletDTO.setCurrencyCode(p.getWallet().getCurrencyCode());

        playerDTO.setWalletDTO(walletDTO);
        return playerDTO;
    }
}
