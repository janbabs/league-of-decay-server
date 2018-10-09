package com.janbabs.leagueofdecayserver.service;

import com.janbabs.leagueofdecayserver.exception.NoMatchListException;
import com.janbabs.leagueofdecayserver.exception.UnsupportedLeagueException;
import com.janbabs.leagueofdecayserver.model.Player;
import com.janbabs.leagueofdecayserver.model.SummonerMatches;
import com.janbabs.leagueofdecayserver.utils.LeagueTier;
import com.janbabs.leagueofdecayserver.utils.ServerType;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

@Service
public class AnalyticsService {

    public static final int NUMBEROFDAYSBEFOREDECAYSTARTS = 28;
    private final PlayerService playerService;
    private final RiotGamesApiService riotGamesApiService;

    public AnalyticsService(PlayerService playerService, RiotGamesApiService riotGamesApiService) {
        this.playerService = playerService;
        this.riotGamesApiService = riotGamesApiService;
    }

    public int getTimeDifference(String server, String summonerName) throws UnsupportedLeagueException, IOException, NoMatchListException {
        ServerType type = ServerType.valueOf(server.toUpperCase());
        Player player = playerService.getPlayer(summonerName, server);
        if (!isEligibleForDecay(player.getId(), type)) {
            return -1;
            // TODO: 07.10.2018 valid response for unranked...
        }
        SummonerMatches matches;
        try {
            matches = riotGamesApiService.getMatchList(player.getAccountId(), type);
        } catch (FileNotFoundException e) {
            throw new NoMatchListException("No matches in history!");
        }
        long lastRankedMatchTimestamp = matches.getLastRankedMatchTimestamp();
        Instant now = Instant.now();
        return daysBetweenTwoDates(now, lastRankedMatchTimestamp) - NUMBEROFDAYSBEFOREDECAYSTARTS;
    }


    private int daysBetweenTwoDates(Instant instantStartDate, long endDate) {
        Instant instantEndDate = Instant.ofEpochMilli(endDate);

        Duration between = Duration.between(instantEndDate, instantStartDate);

        long days = between.toDays();

        return (int) days;
    }

    public boolean isEligibleForDecay(Long summonerId, ServerType serverType) throws UnsupportedLeagueException {
        LeagueTier playerLeague = riotGamesApiService.getPlayerLeague(summonerId, serverType);
        if(playerLeague.isEligible()) {
            switch (playerLeague) {
                case MASTER:
                case CHALLANGER:
                    throw new UnsupportedLeagueException("Master and Challenger tier are unsupported");
                default:
                    return true;
            }
        }
        return false;
    }


}
