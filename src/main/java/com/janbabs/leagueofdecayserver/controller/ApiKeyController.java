package com.janbabs.leagueofdecayserver.controller;

import com.janbabs.leagueofdecayserver.service.ConfigService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/api/apiKey")
public class ApiKeyController {
    private ConfigService configService;


    public ApiKeyController(ConfigService configService) {
        this.configService = configService;
    }

    @RequestMapping(method = PUT)
    public void updateKey(@RequestParam(name = "value") String newValue) {
        configService.changeApikeyValue(newValue);
    }
}
