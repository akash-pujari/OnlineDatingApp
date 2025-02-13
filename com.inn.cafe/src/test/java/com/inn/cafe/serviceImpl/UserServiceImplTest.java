package com.inn.cafe.serviceImpl;

import com.inn.cafe.dao.UserDao;
import com.inn.cafe.pojo.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    private UserDao userDao;
    @Mock
    private User user;
    private Map<String, String> requestBody;
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        userDao = mock(UserDao.class);
        userServiceImpl = new UserServiceImpl();
        userServiceImpl.setUserDao(userDao);
        requestBody = new HashMap<>();
        requestBody.put("name", "akash");
        requestBody.put("contactNumber", "861");
        requestBody.put("email", "akash@gamil.com");
        requestBody.put("password", "8888");
        requestBody.put("status", "ACTIVE");
        requestBody.put("role", "admin");
        user = new User();
        user.setName("akash");
        user.setRole("admin");
        user.setPassword("8888");
        user.setEmail("akash@gamil.com");
        user.setContactNumber("8861");
        user.setStatus("ACTIVE");
    }

    @Test
    void testSignUp_userAlreadyExists() {
        when(userDao.findByEmailId("akash@gamil.com")).thenReturn(user);
        ResponseEntity<String> result = userServiceImpl.signUp(requestBody);
        assertEquals(HttpStatus.BAD_REQUEST,result.getStatusCode());
    }

    @Test
    void testSignUp_forNewUser() {
        ResponseEntity<String> result = userServiceImpl.signUp(requestBody);
        assertEquals(HttpStatus.OK,result.getStatusCode());
    }

    @Test
    void testSignUp_withInvalidRequestBody() {
        ResponseEntity<String> result = userServiceImpl.signUp(new HashMap<>());
        assertEquals(HttpStatus.BAD_REQUEST,result.getStatusCode());
    }
}