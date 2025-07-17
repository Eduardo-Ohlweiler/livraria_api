package com.livraria_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbbidenException extends RuntimeException {
  public ForbbidenException(String message) {
    super(message);
  }
}
