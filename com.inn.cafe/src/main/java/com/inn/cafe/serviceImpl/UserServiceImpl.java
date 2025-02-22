package com.inn.cafe.serviceImpl;

import com.inn.cafe.constants.CafeConstants;
import com.inn.cafe.dao.UserDao;
import com.inn.cafe.jwt.CustomerUserDetailsService;
import com.inn.cafe.jwt.JwtUtil;
import com.inn.cafe.pojo.User;
import com.inn.cafe.service.UserService;
import com.inn.cafe.utils.CafeUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Setter
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestBody) {
        log.info("Into the signUp! Request body: {}", requestBody);
        try {
            User user;
            if (validateRequestBody(requestBody)) {
                log.info("Into validation!");
                user = userDao.findByEmailId(requestBody.get("email"));
                if (Objects.isNull(user)) {
                    user = getUserFromRequestMap(requestBody);
                    userDao.save(user);
                    log.info("user Inserted successfully!");
                    return CafeUtils.getResponse(CafeConstants.USER_INSERTED, HttpStatus.OK);
                } else {
                    return CafeUtils.getResponse(CafeConstants.USER_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponse(CafeConstants.INVALID_REQUEST, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return CafeUtils.getResponse("Something went wrong while validating", HttpStatus.BAD_REQUEST);

        }
    }

    private boolean validateRequestBody(Map<String, String> requestBody) {
        return requestBody.containsKey("name") && requestBody.containsKey("contactNumber") &&
                requestBody.containsKey("email") && requestBody.containsKey("password");
    }

    private User getUserFromRequestMap(Map<String, String> requestBody) {
        User user = new User();
        user.setName(requestBody.get("name"));
        user.setContactNumber(requestBody.get("contactNumber"));
        log.info("Setting contactNumber: {}", requestBody.get("contactNumber")); // Log contactNumber
        user.setEmail(requestBody.get("email"));
        user.setPassword(requestBody.get("password"));
        user.setStatus(requestBody.get("status"));
        user.setRole(requestBody.get("role"));
        return user;

    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login");
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));
            if (auth.isAuthenticated()) {
             if(customerUserDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")){
                 return new ResponseEntity<>("{\"token\":\""+jwtUtil.generateToken(customerUserDetailsService.getUserDetail().getEmail()+"\"}",customerUserDetailsService.getUserDetail().getRole()),HttpStatus.OK);
             }
             else {
                 return new ResponseEntity<String>("{\"message\":\"" + "wait for admin approval."+"\"}",HttpStatus.BAD_REQUEST);
             }
            }
        } catch (Exception ex) {
            log.error("{ }", ex);
        }
        return new ResponseEntity<String>("{\"message\":\"" + "Bad credentials."+"\"}",HttpStatus.BAD_REQUEST);
    }
}
