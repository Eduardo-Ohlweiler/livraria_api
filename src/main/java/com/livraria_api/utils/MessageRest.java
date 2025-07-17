package com.livraria_api.utils;

import org.springframework.http.HttpStatus;

public class MessageRest {

    private HttpStatus httpStatus;
    private String message;

    public MessageRest(String message) {
        this.message = message;
        this.setHttpStatus(HttpStatus.OK);
    }

    public MessageRest(String message, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.message    = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
