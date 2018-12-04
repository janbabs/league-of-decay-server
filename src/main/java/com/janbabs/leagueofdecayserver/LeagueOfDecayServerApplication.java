package com.janbabs.leagueofdecayserver;

import com.janbabs.leagueofdecayserver.repository.UserRepository;
import com.janbabs.leagueofdecayserver.user.CustomUserDetails;
import com.janbabs.leagueofdecayserver.user.Role;
import com.janbabs.leagueofdecayserver.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class LeagueOfDecayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeagueOfDecayServerApplication.class, args);
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository) throws Exception {
        if (repository.count() == 0L) {
            String password = passwordEncoder().encode("user");
            repository.save(new User("user", password,
                    Arrays.asList(new Role("USER"), new Role("ACTUATOR"), new Role("ADMIN"))));
        }
        builder.userDetailsService(s -> new CustomUserDetails(repository.findByUsername(s)));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //todo not working in JAVA 10
}
