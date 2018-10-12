package com.janbabs.leagueofdecayserver.controller;

import com.janbabs.leagueofdecayserver.exception.NoMatchListException;
import com.janbabs.leagueofdecayserver.exception.UnsupportedLeagueException;
import com.janbabs.leagueofdecayserver.service.AnalyticsService;
import com.janbabs.leagueofdecayserver.transport.DecayTimerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;


@RestController
@RequestMapping("/api")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/time/{server}/{name}")
    public DecayTimerDTO getDecayTimer(@PathVariable("server") String server,
                                       @PathVariable("name") String summonerName) throws IOException, NoMatchListException
    {
        return analyticsService.getDecayTimer(summonerName, server);
    }

    @ExceptionHandler(UnsupportedLeagueException.class)
    public String handleException (UnsupportedLeagueException e) {
        return e.getMessage();
    }

    @ExceptionHandler(NoMatchListException.class)
    public ResponseEntity handleException (NoMatchListException e) {
        return new ResponseEntity(e, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleException (IOException e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
