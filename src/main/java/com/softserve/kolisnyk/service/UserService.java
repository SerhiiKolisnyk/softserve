package com.softserve.kolisnyk.service;

import com.softserve.kolisnyk.model.User;
import com.softserve.kolisnyk.model.UserAuthToken;

public interface UserService {

  void add(User user);
  void save(UserAuthToken userAuthToken);
  User getById(int id);
  void update(User user);
  void delete(Integer id);
  User getByEmail(String email);
}
