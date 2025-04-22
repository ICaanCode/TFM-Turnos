package com.unir.turnos.domain.exception;

public class InactiveServiceException extends RuntimeException {
  public InactiveServiceException(String message) {
    super(message);
  }
}
