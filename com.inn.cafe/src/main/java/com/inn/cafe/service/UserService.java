package com.inn.cafe.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    ResponseEntity<String> signUp(Map<String, String> requestBody);
    ResponseEntity<String> login(Map<String, String> requestMap);
}
