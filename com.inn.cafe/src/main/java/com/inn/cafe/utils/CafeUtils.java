package com.inn.cafe.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.inn.cafe.constants.CafeConstants.SOMETHING_WENT_WRONG;

public class CafeUtils {

    private CafeUtils() {

    }
    public static ResponseEntity<String> getResponse(String message, HttpStatus httpStatus) {
        return new ResponseEntity<>(message, httpStatus);
    }
}
