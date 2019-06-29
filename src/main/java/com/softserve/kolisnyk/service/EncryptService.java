package com.softserve.kolisnyk.service;

public interface EncryptService {

  String hashPassword(String plainTextPassword);

  boolean checkPass(String plainPassword, String hashedPassword);

  String generateRememberMeToken();

  String generateSelector();

  String hashRememberMeToken(String token);

  boolean checkRememberMeToken(String plainToken, String hashedToken);
}
