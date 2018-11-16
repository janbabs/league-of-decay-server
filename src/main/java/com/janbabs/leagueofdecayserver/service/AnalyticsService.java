package com.janbabs.leagueofdecayserver.service;

import com.janbabs.leagueofdecayserver.exception.NoCurrentlyPlayedGame;
import com.janbabs.leagueofdecayserver.exception.NoMatchListException;
import com.janbabs.leagueofdecayserver.exception.NoRankedMatchException;
import com.janbabs.leagueofdecayserver.model.Champion;
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
import java.io.UncheckedIOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    public static final int NUMBEROFDAYSBEFOREDECAYSTARTSFORPLANINUMANDDIAMOND = 28;
    private final PlayerService playerService;
    private final RiotGamesApiService riotGamesApiService;
    private final ChampionService championService;

    public AnalyticsService(PlayerService playerService, RiotGamesApiService riotGamesApiService, ChampionService championService) {
        this.playerService = playerService;
        this.riotGamesApiService = riotGamesApiService;
        this.championService = championService;
    }

    private Integer getTimeDifference(Long accountId, ServerType type) throws IOException, NoMatchListException, NoRankedMatchException {
        SummonerMatches matches;
        try {
            matches = riotGamesApiService.getMatchList(accountId, type, 1);
        } catch (FileNotFoundException e) {
            throw new NoMatchListException("No matches in history!");
        }
        long lastRankedMatchTimestamp = matches.getLastRankedMatchTimestamp();
        return NUMBEROFDAYSBEFOREDECAYSTARTSFORPLANINUMANDDIAMOND - daysBetweenTwoDates(Instant.now(), lastRankedMatchTimestamp);
    }

    private int daysBetweenTwoDates(Instant instantStartDate, long endDate) {
        Instant instantEndDate = Instant.ofEpochMilli(endDate);

        Duration between = Duration.between(instantEndDate, instantStartDate);

        long days = between.toDays();

        return (int) days;
    }

    public DecayTimerDTO getDecayTimer(String summonerName, ServerType type)
            throws IOException, NoMatchListException, NoRankedMatchException {
        DecayTimerDTO dto = new DecayTimerDTO();
        Player player = playerService.getPlayer(summonerName, type);

        LeagueTier leagueTier = riotGamesApiService.getPlayerLeague(player.getId(), type).getLeagueTier();
        dto.setLeagueTier(leagueTier.toString());
        dto.setEligibleForDecay(leagueTier.isEligible());

        if (leagueTier.isEligible()) {
            Integer daysBeforeDecay = getTimeDifference(player.getAccountId(), type);
            dto.setDaysBeforeDecay(daysBeforeDecay);
        }
        return dto;
    }

    public List<MatchPlayerDTO> getCurrentMatchDetails(ServerType server, String summonerName) throws IOException, NoCurrentlyPlayedGame {
        List<Participants> participants = Arrays.asList(riotGamesApiService.getParticipants(server, summonerName));
        try {
            List<MatchPlayerDTO> dtos = participants.parallelStream().map
                    (participant -> {
                        try {
                            return getMatchPlayerDTOFromParticipant(participant, server);
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    }).collect(Collectors.toList());
            putChampions(participants, dtos);
            return dtos;
        } catch (UncheckedIOException e) {
            throw new IOException();
        }
    }

    private MatchPlayerDTO getMatchPlayerDTOFromParticipant(Participants participant, ServerType server) throws IOException {
        String name, team, leagueTier, rank;
        int teamNumber = Integer.valueOf(participant.getTeamId());

        name = participant.getSummonerName();
        if (teamNumber == 100) team = "blue";
        else team = "red";
        System.out.println("Downloading player: " + participant.getSummonerName());
        Player player = riotGamesApiService.getPlayer(participant.getSummonerName(), server);
        System.out.println("Downloading league: " + participant.getSummonerName());
        League league = riotGamesApiService.getPlayerLeague(player.getId(), server);
        leagueTier = league.getLeagueTier().toString();
        rank = league.getRank();

        MatchPlayerDTO.PlayerDTOBuilder builder = new MatchPlayerDTO.PlayerDTOBuilder();
        return builder.summonerName(name).leagueTier(leagueTier).rank(rank).team(team).build();
    }

    private void putChampions(List<Participants> participants, List<MatchPlayerDTO> dtos) {
        List<Participants> copyParticipants = new ArrayList<>(participants);
        List<Integer> ids = copyParticipants.stream().map(Participants::getChampionId).collect(Collectors.toList());
        List<Champion> championsByIds = championService.getChampionsByIds(ids);
        for (MatchPlayerDTO dto :
                dtos) {
            for (Participants participant :
                    copyParticipants) {
                if (dto.getSummonerName().equals(participant.getSummonerName())) {
                    for (Champion champion :
                            championsByIds) {
                        if (champion.getId().equals(participant.getChampionId())) {
                            dto.setChampionName(champion.getName());
                            break;
                        }
                    }
                    copyParticipants.remove(participant);
                    break;
                }
            }
        }
    }
}

