package com.softserve.kolisnyk.dao;

import com.softserve.kolisnyk.model.User;
import com.softserve.kolisnyk.model.UserAuthToken;
import com.sun.istack.internal.NotNull;
import java.sql.SQLException;

public interface UserDAO {

  User getUserByCredentials(String email, String password) throws SQLException;

  User getUserByEmail(String email) throws SQLException;



  void addUser(@NotNull User user) throws SQLException;
  void addUserUserAuthToken(@NotNull UserAuthToken userAuthToken) throws SQLException;


  UserAuthToken findBySelector(String selector) throws SQLException;
  void update(UserAuthToken userAuthToken) throws SQLException;
  void delete(int userAuthTokenId) throws SQLException;

  ;
}
