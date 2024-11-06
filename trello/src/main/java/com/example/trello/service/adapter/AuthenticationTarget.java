package com.example.trello.service.adapter;

//target 
//The "Power Cable"

/*
My version of my webpages authentication side
 */
public interface AuthenticationTarget {
    boolean login(String username, String password);
    void logout();
    boolean authorize(String role);

}