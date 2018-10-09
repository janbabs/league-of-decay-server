package com.janbabs.leagueofdecayserver.repository;

import com.janbabs.leagueofdecayserver.model.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyRepository extends JpaRepository <ApiKey, Integer> {
}
