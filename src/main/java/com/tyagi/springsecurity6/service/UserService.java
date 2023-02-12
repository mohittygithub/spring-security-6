package com.tyagi.springsecurity6.service;

import com.tyagi.springsecurity6.dto.ApiResponse;
import com.tyagi.springsecurity6.model.User;

public interface UserService {

    ApiResponse create(User user);
}
