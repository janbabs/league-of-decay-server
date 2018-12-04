package com.janbabs.leagueofdecayserver.repository;

import com.janbabs.leagueofdecayserver.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
