package com.janbabs.leagueofdecayserver.controller;

import com.janbabs.leagueofdecayserver.exception.NoCurrentlyPlayedGame;
import com.janbabs.leagueofdecayserver.exception.NoMatchListException;
import com.janbabs.leagueofdecayserver.exception.NoRankedMatchException;
import com.janbabs.leagueofdecayserver.exception.UnsupportedServerType;
import com.janbabs.leagueofdecayserver.service.AnalyticsService;
import com.janbabs.leagueofdecayserver.transport.DecayTimerDTO;
import com.janbabs.leagueofdecayserver.transport.MatchPlayerDTO;
import com.janbabs.leagueofdecayserver.utils.ServerType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
            throws IOException, NoMatchListException, NoRankedMatchException {
        return analyticsService.getDecayTimer(summonerName, server);
    }

    @GetMapping("/playedMatchDetail/{server}/{summonerName}")
    public MatchPlayerDTO[] getMatchPLayer(@PathVariable String server, @PathVariable String summonerName) throws UnsupportedServerType, IOException, NoCurrentlyPlayedGame {
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
        return new ResponseEntity(e, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleException (IOException e) {
        e.printStackTrace();
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnsupportedServerType.class)
    public ResponseEntity handleException(UnsupportedServerType e) {
        e.printStackTrace();
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoCurrentlyPlayedGame.class)
    public ResponseEntity handleException(NoCurrentlyPlayedGame e) {
        e.printStackTrace();
        return new ResponseEntity("No current game played", HttpStatus.NO_CONTENT);
    }
}
