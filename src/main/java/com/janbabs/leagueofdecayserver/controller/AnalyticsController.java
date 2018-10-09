package com.janbabs.leagueofdecayserver.controller;

import com.janbabs.leagueofdecayserver.exception.NoMatchListException;
import com.janbabs.leagueofdecayserver.exception.UnsupportedLeagueException;
import com.janbabs.leagueofdecayserver.service.AnalyticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;


@RestController
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/time/{server}/{name}")
    public int timeDifference(@PathVariable("server") String server, @PathVariable("name") String summonerName) throws UnsupportedLeagueException, IOException, NoMatchListException {
        return analyticsService.getTimeDifference(server, summonerName);
    }

    @ExceptionHandler(UnsupportedLeagueException.class)
    public String handleException (UnsupportedLeagueException e) {
        return e.getMessage();
    }

    @ExceptionHandler(NoMatchListException.class)
    public ResponseEntity handleException (NoMatchListException e) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleException (IOException e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
