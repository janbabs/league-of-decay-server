package com.janbabs.leagueofdecayserver.repository;

import com.janbabs.leagueofdecayserver.model.Player;
import com.janbabs.leagueofdecayserver.utils.ServerType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository <Player, Long> {
    Player findBySummonerNameAndServerType(String summonerName, ServerType serverType);
}
