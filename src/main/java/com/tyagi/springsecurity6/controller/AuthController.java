package com.tyagi.springsecurity6.controller;

import com.tyagi.springsecurity6.dto.ApiResponse;
import com.tyagi.springsecurity6.dto.AuthenticationRequest;
import com.tyagi.springsecurity6.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@SuppressWarnings("unused")
@Slf4j
@CrossOrigin
@RestController
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public ApiResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws BadCredentialsException {
        log.info("authenticationRequest===> " + authenticationRequest);
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        log.info("authenticate=====> " + authenticate);


        if (!authenticate.isAuthenticated()) {
            throw new UsernameNotFoundException("User Not Found: " + authenticationRequest.getUsername());
        }

        return new ApiResponse(null, jwtService.generateToken(authenticationRequest.getUsername()), true, 1, null);
    }
}
