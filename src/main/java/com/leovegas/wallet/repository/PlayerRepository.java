package com.leovegas.wallet.repository;

import com.leovegas.wallet.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Player findByPlayerNameIgnoreCase(String playerName);
}
