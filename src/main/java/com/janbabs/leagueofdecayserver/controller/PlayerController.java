package com.janbabs.leagueofdecayserver.controller;

import com.janbabs.leagueofdecayserver.exception.InvalidApiKeyException;
import com.janbabs.leagueofdecayserver.exception.UnsupportedServerType;
import com.janbabs.leagueofdecayserver.model.Player;
import com.janbabs.leagueofdecayserver.service.PlayerService;
import com.janbabs.leagueofdecayserver.utils.ServerType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("api/player")
public class PlayerController {
    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{type}/{name}")
    Player readSummoner(@PathVariable("type") String serverString, @PathVariable("name") String summonerName) throws IOException, UnsupportedServerType {
        ServerType type;
        try {
            type = ServerType.valueOf(serverString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new UnsupportedServerType("Unsupported server type!");
        }
        return playerService.getPlayer(summonerName, type);
    }

    @DeleteMapping("/all")
    void deleteAllSummoners() {
        playerService.deleteAll();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleException(Exception e) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(InvalidApiKeyException.class)
    public String handleInvalidApiKeyException(InvalidApiKeyException e) {
        return e.getMessage();
    }

    @ExceptionHandler(UnsupportedServerType.class)
    public ResponseEntity handleException(UnsupportedServerType e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidApiKeyException.class)
    public ResponseEntity handleException(InvalidApiKeyException e) {
        System.out.println(e.getMessage());
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
