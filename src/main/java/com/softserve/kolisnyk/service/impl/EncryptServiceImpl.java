package com.softserve.kolisnyk.service.impl;

import com.softserve.kolisnyk.service.EncryptService;
import org.mindrot.jbcrypt.BCrypt;

 public class EncryptServiceImpl implements EncryptService {

  public String hashPassword(String plainTextPassword) {
 return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
//    return plainTextPassword;
  }

  public boolean checkPass(String plainPassword, String hashedPassword) {
    return BCrypt.checkpw(plainPassword, hashedPassword);
//    System.out.println("wanted:"+hashedPassword+"; but get"+plainPassword);
//    return plainPassword.equalsIgnoreCase(hashedPassword);
  }

  public String generateRememberMeToken() {
    return this.generateRandomString(12);
  }

  public String generateSelector() {
    return this.generateRandomString(10);
  }

  private String generateRandomString(int n) {
    // chose a Character random from this String
    String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        + "0123456789"
        + "abcdefghijklmnopqrstuvxyz";

    // create StringBuffer size of AlphaNumericString
    StringBuilder sb = new StringBuilder(n);

    for (int i = 0; i < n; i++) {

      // generate a random number between
      // 0 to AlphaNumericString variable length
      int index
          = (int) (AlphaNumericString.length()
          * Math.random());

      // add Character one by one in end of sb
      sb.append(AlphaNumericString
          .charAt(index));
    }

    return sb.toString();
  }

  public String hashRememberMeToken(String token) {
    return BCrypt.hashpw(token, BCrypt.gensalt());
  }

  public boolean checkRememberMeToken(String plainToken, String hashedToken) {
    return BCrypt.checkpw(plainToken, hashedToken);
  }
}
