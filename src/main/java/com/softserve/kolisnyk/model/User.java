package com.softserve.kolisnyk.model;

public class User {
  private int id;
  private String name;
  private String surName;
  private String email;
  private String password;
  private String address;
  private String role;

  public User(int id, String name, String surName, String email, String password,
      String address, String role) {
    this.id = id;
    this.name = name;
    this.surName = surName;
    this.email = email;
    this.password = password;
    this.address = address;
    this.role = role;
  }

  public User(int id, String name, String surName, String email, String address,
      String role) {
    this.id = id;
    this.name = name;
    this.surName = surName;
    this.email = email;
    this.address = address;
    this.role = role;
  }

  public User(String name, String surName, String email, String address, String role) {
    this.name = name;
    this.surName = surName;
    this.email = email;
    this.address = address;
    this.role = role;
  }

  public User( String name, String surName, String email, String password,
      String address, String role) {
    this.name = name;
    this.surName = surName;
    this.email = email;
    this.password = password;
    this.address = address;
    this.role = role;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurName() {
    return surName;
  }

  public void setSurName(String surName) {
    this.surName = surName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", surName='" + surName + '\'' +
        ", email='" + email + '\'' +
        ", address='" + address + '\'' +
        ", role='" + role + '\'' +
        '}';
  }
}
