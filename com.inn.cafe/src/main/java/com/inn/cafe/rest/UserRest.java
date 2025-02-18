package com.inn.cafe.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RequestMapping("/user")
public interface UserRest {
    @PostMapping("/signup")
    ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestBody);

    @PostMapping(path="/login")
    ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap);

}
