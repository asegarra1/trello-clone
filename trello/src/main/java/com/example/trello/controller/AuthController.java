package com.example.trello.controller;


import com.example.trello.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/*
Handles all http requesets for everything associated with Authentication.
Works directly with AuthService
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authenticationService;

    @Autowired
    public AuthController(AuthService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        boolean isAuthenticated = authenticationService.login(username, password);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/authorize")
    public ResponseEntity<String> authorize(@RequestParam String role) {
        if (authenticationService.authorize(role)) {
            return ResponseEntity.ok("Authorization successful");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        authenticationService.logoutUser();
        return ResponseEntity.ok("Logout successful");
    }
}