package com.tyagi.springsecurity6.serviceImpl;

import com.tyagi.springsecurity6.dto.ApiResponse;
import com.tyagi.springsecurity6.model.User;
import com.tyagi.springsecurity6.repository.RoleRepository;
import com.tyagi.springsecurity6.repository.UserRepository;
import com.tyagi.springsecurity6.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public ApiResponse create(User user) {
        User existingUser = userRepository.findByUsername(user.getEmail()).orElse(null);
        if (existingUser != null) {
            throw new NullPointerException("User Already Exists");
        }

        user.setRole(roleRepository.findByName("ROLES_USER"));
        user.setUsername(user.getEmail());
        userRepository.save(user);

        return new ApiResponse(user.getId(), "", true, 1, Arrays.asList(user));
    }
}
