package org.example.Exception;

import org.springframework.http.HttpStatusCode;

public class UserException extends RuntimeException {

    public static final String USER_NOT_FOUND = "user not found";
    public static final String USER_EXIST = "user already exist";
    public HttpStatusCode statusCode;

    public UserException(final String message) {
        super(message);
    }

    public UserException(final HttpStatusCode statusCode) {
        this.statusCode = statusCode;
    }
}