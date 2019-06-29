package com.softserve.kolisnyk.exception;

public class FailedToLoginException extends Exception {

  public FailedToLoginException() {
  }

  public FailedToLoginException(String message) {
    super(message);
  }

  public FailedToLoginException(String message, Throwable cause) {
    super(message, cause);
  }

  public FailedToLoginException(Throwable cause) {
    super(cause);
  }

  public FailedToLoginException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
