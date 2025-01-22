package com.hmso.premier_league.repository;

import com.hmso.premier_league.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, String> {
}
