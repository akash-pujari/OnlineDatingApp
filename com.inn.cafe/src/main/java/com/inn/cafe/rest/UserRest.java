package com.inn.cafe.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/user")
public interface UserRest {
    @PostMapping("/signup")
    ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestBody);
    @PostMapping("/login/{username}/{password}")
    ResponseEntity<String> login(@PathVariable String username,@PathVariable String password, @RequestParam(name = "email", required = true) String email);
    @PutMapping("/forgotPassword/{username}")
    ResponseEntity<String> forgotPassword(@PathVariable String username,@RequestParam(name="newPassword",required = true) String newPassword, @RequestParam(name = "email", required = true) String email);

}
