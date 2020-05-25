package com.leovegas.wallet.repository;

import com.leovegas.wallet.model.Player;
import com.leovegas.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findByPlayer(Player player);
}
