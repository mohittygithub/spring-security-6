package com.tyagi.springsecurity6.security;

import com.tyagi.springsecurity6.model.User;
import com.tyagi.springsecurity6.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("======CustomUserDetailsService======");

            Optional<User> users = userRepository.findByUsername(username);

            User user = users.orElse(null);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with username : " + username);
            }
            return new CustomUserDetails(user);
    }
}
