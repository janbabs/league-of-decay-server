package com.janbabs.leagueofdecayserver.repository;

import com.janbabs.leagueofdecayserver.model.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends JpaRepository <Config, Integer> {
    Config findByName(String name);
}
