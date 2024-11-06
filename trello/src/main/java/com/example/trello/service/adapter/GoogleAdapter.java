package com.example.trello.service.adapter;


//Adapter
//The "Plug Converter"

import org.springframework.stereotype.Service;


/*
The class that implements my interface but returns googles API, acting as the bridge (adapter)
 */
@Service
public class GoogleAdapter implements AuthenticationTarget {
    
    private final GoogleAdaptee googleAuthService;

    public GoogleAdapter(GoogleAdaptee googleAuthService) {

        this.googleAuthService = googleAuthService;
    }

    @Override
    public boolean login(String username, String password) {

        return googleAuthService.login(username, password);
    }

    @Override
    public void logout() {

        googleAuthService.logout();
    }

    @Override
    public boolean authorize(String role) {
        return googleAuthService.authorize(role);
    }
}