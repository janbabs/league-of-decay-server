package com.janbabs.leagueofdecayserver.repository;

import com.janbabs.leagueofdecayserver.model.Champion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionRepository extends JpaRepository<Champion, Integer> {

}
