package com.inn.cafe.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    ResponseEntity<String> signUp(Map<String, String> requestBody);
    ResponseEntity<String> login(String username,String password,String email);
    ResponseEntity<String> forgotPassword(String username,String newPassword,String email);
}
