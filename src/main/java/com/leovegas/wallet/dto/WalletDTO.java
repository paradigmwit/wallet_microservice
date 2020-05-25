package com.leovegas.wallet.dto;

public class WalletDTO {

    private Double balance;
    private String currencyCode;

    public WalletDTO() {
    }

    public WalletDTO(Double balance, String currencyCode) {
        this.balance = balance;
        this.currencyCode = currencyCode;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "WalletDTO{" +
                "balance=" + balance +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }
}
