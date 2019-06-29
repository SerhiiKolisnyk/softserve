package com.softserve.kolisnyk.model;

public class UserAuthToken {
  private int id;
  private String selector;
  private String validator;
  private User user;

  public UserAuthToken(int id, String selector, String validator,
      User user) {
    this.id = id;
    this.selector = selector;
    this.validator = validator;
    this.user = user;
  }

  public UserAuthToken() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getSelector() {
    return selector;
  }

  public void setSelector(String selector) {
    this.selector = selector;
  }

  public String getValidator() {
    return validator;
  }

  public void setValidator(String validator) {
    this.validator = validator;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
