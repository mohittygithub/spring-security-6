package com.tyagi.springsecurity6.security;

import com.tyagi.springsecurity6.model.Role;
import com.tyagi.springsecurity6.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final String username;
    private final String password;
    private final Boolean isActive;
    private final Role role;

    private final List<GrantedAuthority> authorities;


    public CustomUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.isActive = user.getIsActive();
        this.role = user.getRole();
        this.authorities =
                Arrays
                        .stream(user.getRole().getName().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

}