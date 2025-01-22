package com.hmso.premier_league.repository;

import com.hmso.premier_league.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, String> {
    void deleteByName(String playerName);
    Optional<Player> findByName(String name);
}
