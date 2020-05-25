package com.leovegas.wallet.dto;

import java.util.Date;

public class PlayerDTO {
    private String playerName;
    private Date created;
    private WalletDTO walletDTO;

    public PlayerDTO() {
    }

    public PlayerDTO(String playerId, String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public WalletDTO getWalletDTO() {
        return walletDTO;
    }

    public void setWalletDTO(WalletDTO walletDTO) {
        this.walletDTO = walletDTO;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "playerName='" + playerName + '\'' +
                ", created=" + created +
                ", walletDTO=" + walletDTO +
                '}';
    }
}
