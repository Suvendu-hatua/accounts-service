package com.microservices.eazybank.Accounts.exception;

public class CustomerAlreadyExistsException extends RuntimeException {

  public CustomerAlreadyExistsException(String message) {
    super(message);
  }
}
