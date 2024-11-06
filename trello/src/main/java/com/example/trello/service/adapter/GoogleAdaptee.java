package com.example.trello.service.adapter;


import org.springframework.stereotype.Service;

//Adaptee
//The "Outlet"
/*
Googles versions of authentication (API)
 */
@Service
public class GoogleAdaptee {

    public boolean login(String username, String password) {
        // TODO: Implement actual Google API call for login
        return true;
    }

    public void logout() {
        // TODO: Implement actual Google API call for logout
    }

    public boolean authorize(String role) {
        // TODO: Implement actual Google API call for role authorization
        return "ADMIN".equals(role); // Stubbed response for testing
    }
}