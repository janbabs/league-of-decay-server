package com.janbabs.leagueofdecayserver.controller;

import com.janbabs.leagueofdecayserver.exception.*;
import com.janbabs.leagueofdecayserver.service.AnalyticsService;
import com.janbabs.leagueofdecayserver.transport.DecayTimerDTO;
import com.janbabs.leagueofdecayserver.transport.MatchPlayerDTO;
import com.janbabs.leagueofdecayserver.utils.ServerType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/time/{server}/{name}")
    public DecayTimerDTO getDecayTimer(@PathVariable("server") String server,
                                       @PathVariable("name") String summonerName)
            throws IOException, NoMatchListException, NoRankedMatchException, UnsupportedServerType {
        ServerType type;
        try {
            type = ServerType.valueOf(server.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnsupportedServerType("Unsupported server type!");
        }
        return analyticsService.getDecayTimer(summonerName, type);
    }

    @GetMapping("/playedMatchDetail/{server}/{summonerName}")
    public List<MatchPlayerDTO> getMatchPLayer(@PathVariable String server, @PathVariable String summonerName) throws UnsupportedServerType, IOException, NoCurrentlyPlayedGame {
        ServerType type;
        try {
            type = ServerType.valueOf(server.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnsupportedServerType("Unsupported server type!");
        }
        return analyticsService.getCurrentMatchDetails(type, summonerName);
    }


    @ExceptionHandler(NoMatchListException.class)
    public ResponseEntity handleException (NoMatchListException e) {
        System.out.println(e.getMessage());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(InvalidApiKeyException.class)
    public ResponseEntity handleException(InvalidApiKeyException e) {
        System.out.println(e.getMessage());
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleException (IOException e) {
        e.printStackTrace();
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnsupportedServerType.class)
    public ResponseEntity handleException(UnsupportedServerType e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoCurrentlyPlayedGame.class)
    public ResponseEntity handleException(NoCurrentlyPlayedGame e) {
        e.printStackTrace();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
