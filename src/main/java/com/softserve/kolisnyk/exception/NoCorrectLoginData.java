package com.softserve.kolisnyk.exception;

public class NoCorrectLoginData extends FailedToLoginException {

  public NoCorrectLoginData() {
  }

  public NoCorrectLoginData(String message) {
    super(message);
  }

  public NoCorrectLoginData(String message, Throwable cause) {
    super(message, cause);
  }

  public NoCorrectLoginData(Throwable cause) {
    super(cause);
  }

  public NoCorrectLoginData(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
