package com.leovegas.wallet.repository;

import com.leovegas.wallet.model.Player;
import com.leovegas.wallet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

    List<Transaction> findByPlayer(Player player);
}
