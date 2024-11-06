package com.example.trello.service;

import com.example.trello.service.adapter.AuthenticationTarget;
import org.springframework.stereotype.Service;

/*
My auth service that handles the functionality of my webpages authentication
To be used with a controller
 */

@Service
public class AuthService {
    private final AuthenticationTarget authAdapter;

    public AuthService(AuthenticationTarget authAdapter) {
        this.authAdapter = authAdapter;
    }

    public boolean login(String username, String password) {
        return authAdapter.login(username, password);
    }

    public boolean authorize(String role) {
        return authAdapter.authorize(role);
    }

    public void logoutUser() {
        authAdapter.logout();
    }
}