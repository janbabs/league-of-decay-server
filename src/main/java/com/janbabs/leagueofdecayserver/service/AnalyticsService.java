package com.janbabs.leagueofdecayserver.service;

import com.janbabs.leagueofdecayserver.exception.NoCurrentlyPlayedGame;
import com.janbabs.leagueofdecayserver.exception.NoMatchListException;
import com.janbabs.leagueofdecayserver.exception.NoRankedMatchException;
import com.janbabs.leagueofdecayserver.model.Player;
import com.janbabs.leagueofdecayserver.model.SummonerMatches;
import com.janbabs.leagueofdecayserver.riotgamesModels.League;
import com.janbabs.leagueofdecayserver.riotgamesModels.Participants;
import com.janbabs.leagueofdecayserver.transport.DecayTimerDTO;
import com.janbabs.leagueofdecayserver.transport.MatchPlayerDTO;
import com.janbabs.leagueofdecayserver.utils.LeagueTier;
import com.janbabs.leagueofdecayserver.utils.ServerType;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AnalyticsService {

    public static final int NUMBEROFDAYSBEFOREDECAYSTARTSFORPLANINUMANDDIAMOND = 28;
    private final PlayerService playerService;
    private final RiotGamesApiService riotGamesApiService;

    public AnalyticsService(PlayerService playerService, RiotGamesApiService riotGamesApiService) {
        this.playerService = playerService;
        this.riotGamesApiService = riotGamesApiService;
    }

    private Integer getTimeDifference(Long accountId, ServerType type) throws IOException, NoMatchListException, NoRankedMatchException {
        SummonerMatches matches;
        try
        {
            matches = riotGamesApiService.getMatchList(accountId, type, 1);
        }
        catch (FileNotFoundException e)
        {
            throw new NoMatchListException("No matches in history!");
        }
        long lastRankedMatchTimestamp = matches.getLastRankedMatchTimestamp();
        return  NUMBEROFDAYSBEFOREDECAYSTARTSFORPLANINUMANDDIAMOND - daysBetweenTwoDates(Instant.now(), lastRankedMatchTimestamp);
    }

    private int daysBetweenTwoDates(Instant instantStartDate, long endDate)
    {
        Instant instantEndDate = Instant.ofEpochMilli(endDate);

        Duration between = Duration.between(instantEndDate, instantStartDate);

        long days = between.toDays();

        return (int) days;
    }

    public DecayTimerDTO getDecayTimer(String summonerName, String serverString)
            throws IOException, NoMatchListException, NoRankedMatchException {
        DecayTimerDTO dto = new DecayTimerDTO();

        ServerType type = ServerType.valueOf(serverString.toUpperCase());
        Player player = playerService.getPlayer(summonerName, serverString);

        LeagueTier leagueTier = riotGamesApiService.getPlayerLeague(player.getId(), type).getLeagueTier();
        dto.setLeagueTier(leagueTier.toString());
        dto.setEligibleForDecay(leagueTier.isEligible());

        if (leagueTier.isEligible()) {
            Integer daysBeforeDecay = getTimeDifference(player.getAccountId(), type);
            dto.setDaysBeforeDecay(daysBeforeDecay);
        }
        return dto;
    }

    public MatchPlayerDTO[] getCurrentMatchDetails(ServerType server, String summonerName) throws IOException, NoCurrentlyPlayedGame {
//        List<Participants> participants = Arrays.asList(riotGamesApiService.getParticipants(server, summonerName));
//        List<MatchPlayerDTO> dtos = new ArrayList();

        Participants[] participants = riotGamesApiService.getParticipants(server, summonerName);
        MatchPlayerDTO[] dtos = new MatchPlayerDTO[participants.length];

        String name;
        String championName;
        String team;
        String leagueTier;
        String rank;
        int teamNumber;
        League league;
        Player player;

        for (int i = 0; i < participants.length; i++) {
                name = participants[i].getSummonerName();
                teamNumber = Integer.valueOf(participants[i].getTeamId());
                if (teamNumber == 100) team = "blue";
                else team = "red";
                player = riotGamesApiService.getPlayer(participants[i].getSummonerName(), server);
                league = riotGamesApiService.getPlayerLeague(player.getId(), server);
                leagueTier = league.getLeagueTier().toString();
                rank = league.getRank();

                MatchPlayerDTO.PlayerDTOBuilder builder = new MatchPlayerDTO.PlayerDTOBuilder();
                dtos[i] = builder.summonerName(name).leagueTier(leagueTier).rank(rank).team(team).build();
        }

//        participants.stream().map(e -> {
//
//        })
//
        return dtos;

    }
}
