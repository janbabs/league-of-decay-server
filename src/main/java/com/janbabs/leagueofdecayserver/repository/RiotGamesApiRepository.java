package com.janbabs.leagueofdecayserver.repository;

import com.janbabs.leagueofdecayserver.exception.InvalidApiKeyException;
import com.janbabs.leagueofdecayserver.exception.NoMatchListException;
import com.janbabs.leagueofdecayserver.service.ApiKeyService;
import com.janbabs.leagueofdecayserver.utils.ServerType;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Repository
public class RiotGamesApiRepository {
    public static final String SummonerByIdURL = "api.riotgames.com/lol/summoner/v3/summoners/by-name/";
    public static final String MatchListByAcIdURL = "api.riotgames.com/lol/match/v3/matchlists/by-account/";
    public static final String LeagueByIdURL = "api.riotgames.com/lol/league/v3/positions/by-summoner/";

    private String apiKey;

    private ApiKeyService apiKeyService;

    public RiotGamesApiRepository(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    public String getPlayerJsonFromSummonerName(String summonerName, ServerType serverType) throws IOException {
        String  jsonString;
        summonerName = URLEncoder.encode(summonerName, "UTF-8");
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

    public String getPlayerLeague(long summonerId, ServerType serverType) {
        String  JsonString = null;
        try {
            JsonString = getJSonFromServer("https://" + serverType + "." +
                    LeagueByIdURL + summonerId);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: 03.10.2018 fix exception
        }

        return JsonString;
    }

    public String getJSonFromServer(String urlString) throws IOException {
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

    @PostConstruct
    public void  initIt() {
        apiKey = apiKeyService.getApiKeyValue();
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
}
