package com.janbabs.leagueofdecayserver.service;

import com.janbabs.leagueofdecayserver.model.ApiKey;
import com.janbabs.leagueofdecayserver.repository.ApiKeyRepository;
import com.janbabs.leagueofdecayserver.repository.RiotGamesApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApiKeyService {
    private ApiKeyRepository apiKeyRepository;

    private RiotGamesApiService riotGamesApiService;

    public ApiKeyService(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    public String getApiKeyValue() {
        Optional<ApiKey> optionalApiKey = apiKeyRepository.findById(1);
        ApiKey apiKey = optionalApiKey.get();
        return apiKey.getValue();
    }

    public void changeApiKeyValue(String newValue) {
        apiKeyRepository.save(new ApiKey(1, newValue));
        riotGamesApiService.upDateKey();
    }

    @Autowired
    public void setRiotGamesApiService(@Lazy RiotGamesApiService riotGamesApiService) {
        this.riotGamesApiService = riotGamesApiService;
    }
}
