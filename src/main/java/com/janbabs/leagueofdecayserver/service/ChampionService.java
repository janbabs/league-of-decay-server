package com.janbabs.leagueofdecayserver.service;

import com.janbabs.leagueofdecayserver.model.Champion;
import com.janbabs.leagueofdecayserver.repository.ChampionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChampionService {
    private ChampionRepository championRepository;

    public ChampionService(ChampionRepository championRepository) {
        this.championRepository = championRepository;
    }

    public Map<Integer, Champion> getChampionsByIds(List<Integer> id) {
        return championRepository.findAllById(id);
    }
}
