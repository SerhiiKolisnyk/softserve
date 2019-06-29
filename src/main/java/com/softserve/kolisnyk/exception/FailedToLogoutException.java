package com.softserve.kolisnyk.exception;

public class FailedToLogoutException extends Exception {

  public FailedToLogoutException() {
  }

  public FailedToLogoutException(String message) {
    super(message);
  }

  public FailedToLogoutException(String message, Throwable cause) {
    super(message, cause);
  }

  public FailedToLogoutException(Throwable cause) {
    super(cause);
  }

  public FailedToLogoutException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
