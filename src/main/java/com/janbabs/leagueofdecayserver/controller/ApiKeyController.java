package com.janbabs.leagueofdecayserver.controller;

import com.janbabs.leagueofdecayserver.service.ApiKeyService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/api/apiKey")
public class ApiKeyController {
    private ApiKeyService apiKeyService;


    public ApiKeyController(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @RequestMapping(method = PUT)
    public void updateKey(@RequestParam(name = "value") String newValue) {
        apiKeyService.changeApiKeyValue(newValue);
    }
}
