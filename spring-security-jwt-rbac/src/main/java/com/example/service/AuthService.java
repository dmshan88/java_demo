package com.example.service;

public interface AuthService {

    String login(String username, String password);
    
    Boolean verifyPassword(String username, String password);
}
