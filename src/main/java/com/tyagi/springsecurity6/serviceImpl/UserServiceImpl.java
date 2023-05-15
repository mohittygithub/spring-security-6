package com.tyagi.springsecurity6.serviceImpl;

import com.tyagi.springsecurity6.dto.ApiResponse;
import com.tyagi.springsecurity6.model.User;
import com.tyagi.springsecurity6.record.UserRecord;
import com.tyagi.springsecurity6.repository.RoleRepository;
import com.tyagi.springsecurity6.repository.UserRepository;
import com.tyagi.springsecurity6.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@SuppressWarnings("unused")
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
            throw new UsernameNotFoundException("User Already Exists");
        }

        user.setRole(roleRepository.findByName("ROLES_USER"));
        user.setUsername(user.getEmail());
        userRepository.save(user);

        UserRecord userRecord = new UserRecord(user.getFirstName(), user.getLastName(),user.getEmail(),user.getDob(),user.getUsername(),user.getIsActive(), user.getRole().getName());

        return new ApiResponse(user.getId(), "", true, 1, Collections.singletonList(userRecord));
    }
}
