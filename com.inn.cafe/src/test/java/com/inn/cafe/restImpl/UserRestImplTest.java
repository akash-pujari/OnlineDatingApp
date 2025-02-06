package com.inn.cafe.restImpl;

import com.inn.cafe.service.UserService;
import com.inn.cafe.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserRestImplTest {

    @Mock
    private UserService userService = new UserServiceImpl();

    @Mock
    private ResponseEntity<String> responseEntity;
    Map<String, String> requestBody = new HashMap<>();
    @InjectMocks
    private UserRestImpl userRest = new UserRestImpl();

    @BeforeEach
    void setUp() {
        requestBody.put("name", "akash");
        requestBody.put("contactNumber", "8861502546");
        requestBody.put("email", "pujari@gmail.com");
        requestBody.put("status", "ACTIVE");
        requestBody.put("password", "******");
    }

    @Test
    void testSignUpwith_negativeValue() {
        ResponseEntity<String> res = userRest.signUp(requestBody);
        assertNotNull(res);
        assertEquals(HttpStatusCode.valueOf(500), res.getStatusCode());
    }

}