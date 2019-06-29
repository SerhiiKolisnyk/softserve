package com.softserve.kolisnyk.exception;

public class TokenGenerateException extends Exception {

  public TokenGenerateException() {
  }

  public TokenGenerateException(String message) {
    super(message);
  }

  public TokenGenerateException(String message, Throwable cause) {
    super(message, cause);
  }

  public TokenGenerateException(Throwable cause) {
    super(cause);
  }

  public TokenGenerateException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
