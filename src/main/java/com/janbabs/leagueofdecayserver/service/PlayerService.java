package com.janbabs.leagueofdecayserver.service;

import com.janbabs.leagueofdecayserver.model.Player;
import com.janbabs.leagueofdecayserver.repository.PlayerRepository;
import com.janbabs.leagueofdecayserver.utils.ServerType;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class PlayerService {
    private PlayerRepository playerRepository;
    private RiotGamesApiService riotGamesApiService;

    public PlayerService(PlayerRepository playerRepository, RiotGamesApiService riotGamesApiService) {
        this.playerRepository = playerRepository;
        this.riotGamesApiService = riotGamesApiService;
    }

    public Player getPlayer(String summonerName, String serverTypeInString) throws IOException {
        ServerType serverType = ServerType.valueOf(serverTypeInString.toUpperCase());
        Player player = playerRepository.findBySummonerNameAndServerType(summonerName, serverType);
        if (player != null) {
            return player;
        } else {
            player = riotGamesApiService.getPlayer(summonerName, serverType);
            player = playerRepository.save(player);
        }
        return player;
    }

    public void deleteAll() {
        playerRepository.deleteAll();
    }

}
