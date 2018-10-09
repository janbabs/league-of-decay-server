package com.janbabs.leagueofdecayserver.controller;

import com.janbabs.leagueofdecayserver.exception.InvalidApiKeyException;
import com.janbabs.leagueofdecayserver.model.Player;
import com.janbabs.leagueofdecayserver.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/player")
public class PlayerController {
    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{type}/{name}")
    Player readSummoner(@PathVariable("type") String servertype, @PathVariable("name") String summonerName) throws IOException {
        return playerService.getPlayer(summonerName, servertype);
    }

    @DeleteMapping("/all")
    void deleteAllSummoners() {
        playerService.deleteAll();
    }

    @ExceptionHandler(IOException.class)
    public String handleException(Exception e) {
        //return e.getMessage();
        return "Invalid";
    }

    @ExceptionHandler(InvalidApiKeyException.class)
    public String handleInvalidApiKeyException(InvalidApiKeyException e) {
        return e.getMessage();
    }


}
