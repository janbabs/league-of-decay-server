package com.janbabs.leagueofdecayserver.service;

import com.janbabs.leagueofdecayserver.model.Champion;
import com.janbabs.leagueofdecayserver.repository.ChampionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionService {
    private ChampionRepository championRepository;

    public ChampionService(ChampionRepository championRepository) {
        this.championRepository = championRepository;
    }

    public List<Champion> getChampionsByIds(List<Integer> id) {
        return championRepository.findAllById(id);
    }
}
