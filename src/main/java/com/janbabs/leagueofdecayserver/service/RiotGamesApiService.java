package com.janbabs.leagueofdecayserver.service;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.janbabs.leagueofdecayserver.exception.NoCurrentlyPlayedGame;
import com.janbabs.leagueofdecayserver.exception.NoMatchListException;
import com.janbabs.leagueofdecayserver.model.Player;
import com.janbabs.leagueofdecayserver.model.SummonerMatches;
import com.janbabs.leagueofdecayserver.repository.RiotGamesApiRepository;
import com.janbabs.leagueofdecayserver.riotgamesModels.Participants;
import com.janbabs.leagueofdecayserver.utils.LeagueTier;
import com.janbabs.leagueofdecayserver.utils.ServerType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.janbabs.leagueofdecayserver.utils.LeagueTier.UNRANKED;

@Service
public class RiotGamesApiService {

    private RiotGamesApiRepository riotGamesApiRepository;

    public RiotGamesApiService(RiotGamesApiRepository riotGamesApiRepository) {
        this.riotGamesApiRepository = riotGamesApiRepository;
    }

    public Player getPlayer(String summonerName, ServerType serverType) throws IOException {

        summonerName = summonerName.replaceAll(" ", "%20");

        String JsonString = riotGamesApiRepository.getPlayerJsonFromSummonerName(summonerName, serverType);


        Gson gson = new Gson();
        Player player = gson.fromJson(JsonString, Player.class);
        player.setServerType(serverType);

        return player;
    }


    public SummonerMatches getMatchList(long accountId, ServerType type) throws IOException, NoMatchListException {
        Gson gson = new Gson();
        String jsonString = riotGamesApiRepository.getMatchListJson(accountId, type);
        return gson.fromJson(jsonString, SummonerMatches.class);
        // TODO: 22.10.2018 clean up code
    }

    public SummonerMatches getMatchList(Long accountId, ServerType type, int numberOfGames) throws IOException, NoMatchListException {
        Gson gson = new Gson();
        String jsonString = riotGamesApiRepository.getMatchListJson(accountId, type, numberOfGames);
        return gson.fromJson(jsonString, SummonerMatches.class);
        // TODO: 22.10.2018 clean up code
    }

    public com.janbabs.leagueofdecayserver.riotgamesModels.League getPlayerLeague(long summonerId, ServerType type) {
        Gson gson = new Gson();
        String jsonString = riotGamesApiRepository.getPlayerLeague(summonerId, type);
        List<League> leagues = gson.fromJson(jsonString, new TypeToken<List<League>>(){}.getType());

        LeagueTier tier;
        for (League league: leagues) {
            if (league.queueType.equals("RANKED_SOLO_5x5")) {
                tier = LeagueTier.valueOf(league.tier);
                return new com.janbabs.leagueofdecayserver.riotgamesModels.League(LeagueTier.valueOf(league.tier), league.rank);
            }
        }
        return new com.janbabs.leagueofdecayserver.riotgamesModels.League(UNRANKED, null);
    }

    public void upDateKey() {
        riotGamesApiRepository.updateApiKey();
    }

    public Participants[] getParticipants(ServerType server, String summonerName) throws IOException, NoCurrentlyPlayedGame {
        Gson gson = new Gson();
        Player player = getPlayer(summonerName, server);
        //Geting currentGameInfo
        String json = riotGamesApiRepository.getParticipantsJson(server, player.getId());
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(json).getAsJsonObject();
        //Parsing currentGameInfo to participants
        JsonElement element = obj.get("participants");

        //Creating participants from JsonElement
        Participants[] participants = gson.fromJson(element, Participants[].class);

        return participants;
    }

    static private class League
    {
        private String hotStreak;

        private String leagueName;

        private String freshBlood;

        private String tier;

        private String playerOrTeamId;

        private String leaguePoints;

        private String leagueId;

        private String inactive;

        private String rank;

        private String veteran;

        private String queueType;

        private String losses;

        private String playerOrTeamName;

        private String wins;

        public String getHotStreak ()
        {
            return hotStreak;
        }

        public void setHotStreak (String hotStreak)
        {
            this.hotStreak = hotStreak;
        }

        public String getLeagueName ()
        {
            return leagueName;
        }

        public void setLeagueName (String leagueName)
        {
            this.leagueName = leagueName;
        }

        public String getFreshBlood ()
        {
            return freshBlood;
        }

        public void setFreshBlood (String freshBlood)
        {
            this.freshBlood = freshBlood;
        }

        public String getTier ()
        {
            return tier;
        }

        public void setTier (String tier)
        {
            this.tier = tier;
        }

        public String getPlayerOrTeamId ()
        {
            return playerOrTeamId;
        }

        public void setPlayerOrTeamId (String playerOrTeamId)
        {
            this.playerOrTeamId = playerOrTeamId;
        }

        public String getLeaguePoints ()
        {
            return leaguePoints;
        }

        public void setLeaguePoints (String leaguePoints)
        {
            this.leaguePoints = leaguePoints;
        }

        public String getLeagueId ()
        {
            return leagueId;
        }

        public void setLeagueId (String leagueId)
        {
            this.leagueId = leagueId;
        }

        public String getInactive ()
        {
            return inactive;
        }

        public void setInactive (String inactive)
        {
            this.inactive = inactive;
        }

        public String getRank ()
        {
            return rank;
        }

        public void setRank (String rank)
        {
            this.rank = rank;
        }

        public String getVeteran ()
        {
            return veteran;
        }

        public void setVeteran (String veteran)
        {
            this.veteran = veteran;
        }

        public String getQueueType ()
        {
            return queueType;
        }

        public void setQueueType (String queueType)
        {
            this.queueType = queueType;
        }

        public String getLosses ()
        {
            return losses;
        }

        public void setLosses (String losses)
        {
            this.losses = losses;
        }

        public String getPlayerOrTeamName ()
        {
            return playerOrTeamName;
        }

        public void setPlayerOrTeamName (String playerOrTeamName)
        {
            this.playerOrTeamName = playerOrTeamName;
        }

        public String getWins ()
        {
            return wins;
        }

        public void setWins (String wins)
        {
            this.wins = wins;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [hotStreak = "+hotStreak+", leagueName = "+leagueName+", freshBlood = "+freshBlood+", tier = "+tier+", playerOrTeamId = "+playerOrTeamId+", leaguePoints = "+leaguePoints+", leagueId = "+leagueId+", inactive = "+inactive+", rank = "+rank+", veteran = "+veteran+", queueType = "+queueType+", losses = "+losses+", playerOrTeamName = "+playerOrTeamName+", wins = "+wins+"]";
        }
    }
}
