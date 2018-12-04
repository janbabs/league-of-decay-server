package com.janbabs.leagueofdecayserver.service;

import com.janbabs.leagueofdecayserver.repository.UserRepository;
import com.janbabs.leagueofdecayserver.transport.UserDTO;
import com.janbabs.leagueofdecayserver.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(UserDTO userDTO) {
        userRepository.save(new User(userDTO.getUsername(),
                passwordEncoder.encode(userDTO.getPassword()), userDTO.getRoles()));
    }
}
