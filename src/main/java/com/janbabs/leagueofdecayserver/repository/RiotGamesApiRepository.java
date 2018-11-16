package com.janbabs.leagueofdecayserver.repository;

import com.janbabs.leagueofdecayserver.exception.InvalidApiKeyException;
import com.janbabs.leagueofdecayserver.exception.NoCurrentlyPlayedGame;
import com.janbabs.leagueofdecayserver.exception.NoMatchListException;
import com.janbabs.leagueofdecayserver.service.ConfigService;
import com.janbabs.leagueofdecayserver.utils.ServerType;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Repository
public class RiotGamesApiRepository {
    private static final String SummonerByIdURL = "api.riotgames.com/lol/summoner/v3/summoners/by-name/";
    private static final String MatchListByAcIdURL = "api.riotgames.com/lol/match/v3/matchlists/by-account/";
    private static final String LeagueByIdURL = "api.riotgames.com/lol/league/v3/positions/by-summoner/";
    public static final String CurrentGameInfoBySmId = "api.riotgames.com/lol/spectator/v3/active-games/by-summoner/";

    private String apiKey;

    private ConfigService configService;

    public RiotGamesApiRepository(ConfigService configService) {
        this.configService = configService;
    }

    public String getPlayerJsonFromSummonerName(String summonerName, ServerType serverType) throws IOException {
        String  jsonString;
        jsonString = getJSonFromServer("https://" + serverType + "." +
                        SummonerByIdURL + summonerName);
        return jsonString;
    }

    public String getMatchListJson(long accountId, ServerType type) throws IOException, NoMatchListException {
        String  serverType = type.toString(),
                jsonString;
        try {
            jsonString = getJSonFromServer("https://" + serverType + "." + MatchListByAcIdURL + accountId);
            return jsonString;
        } catch (FileNotFoundException e) {
            throw new NoMatchListException();
        }
    }

    public String getPlayerLeague(long summonerId, ServerType serverType) throws IOException {
        String  JsonString = null;
        try {
            JsonString = getJSonFromServer("https://" + serverType + "." +
                    LeagueByIdURL + summonerId);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return JsonString;
    }

    private String getJSonFromServer(String urlString) throws IOException {
        checkApiKey();
        BufferedReader reader = null;
        try {
            URL url;
            if (urlString.contains("?")) {
                url = new URL(urlString + "&api_key=" + apiKey);
            } else {
                url = new URL(urlString + "?api_key=" + apiKey);
            }

            HttpURLConnection connection = (HttpURLConnection) new URL(url.toString()).openConnection();
            final int responseCode = connection.getResponseCode();
            switch (responseCode) {
                case 403:
                    throw new InvalidApiKeyException("Invalid ApiKey Exception!");
                case 404:
                    throw new FileNotFoundException();
                default:
                    reader = new BufferedReader(new InputStreamReader(url.openStream()));
                    StringBuffer buffer = new StringBuffer();
                    int read;
                    char[] chars = new char[1024];
                    while ((read = reader.read(chars)) != -1)
                        buffer.append(chars, 0, read);
                    return buffer.toString();
            }
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    private void  checkApiKey() throws InvalidApiKeyException {
        if (apiKey == null)
            try{
                apiKey = configService.getApiKeyValue();
            } catch (NullPointerException e) {
                throw new InvalidApiKeyException("No api key in database!");
            }
    }

    public String getMatchListJson(Long accountId, ServerType type, int numberOfGames) throws IOException, NoMatchListException {
        String  serverType = type.toString(),
                jsonString;
        try {
            jsonString = getJSonFromServer("https://" + serverType + "." + MatchListByAcIdURL + accountId +
                    "?endIndex=" + numberOfGames + "&queue=420");
            return jsonString;
        } catch (FileNotFoundException e) {
            throw new NoMatchListException();
        }

    }

    public void updateApiKey() {
        this.apiKey = configService.getApiKeyValue();
    }

    public String getParticipantsJson(ServerType server, Long summonerId) throws NoCurrentlyPlayedGame, IOException {
        String serverType = server.toString(), jsonString;
        try {
            jsonString = getJSonFromServer("https://" + serverType + "." + CurrentGameInfoBySmId + summonerId);
            return jsonString;
        } catch (FileNotFoundException e) {
            throw new NoCurrentlyPlayedGame();
        }
    }
}
