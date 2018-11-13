package com.janbabs.leagueofdecayserver.service;

import com.janbabs.leagueofdecayserver.model.Config;
import com.janbabs.leagueofdecayserver.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {
    private ConfigRepository configRepository;
    private RiotGamesApiService riotGamesApiService;

    public ConfigService(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Autowired
    public void setRiotGamesApiService(@Lazy RiotGamesApiService riotGamesApiService) {
        this.riotGamesApiService = riotGamesApiService;
    }

    public String getApiKeyValue() {
        return configRepository.findByName("apikey").getValue();
    }

    public void changeApikeyValue(String newValue) {
        Config config = configRepository.findByName("apikey");
        if (config == null) {
            config = new Config();
            config.setName("apikey");
        }
        config.setValue(newValue);
        configRepository.save(config);
    }
}
