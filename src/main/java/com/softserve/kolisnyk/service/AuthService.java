package com.softserve.kolisnyk.service;

import com.softserve.kolisnyk.exception.FailedToLoginException;
import com.softserve.kolisnyk.exception.FailedToLogoutException;
import com.softserve.kolisnyk.exception.NoCorrectLoginData;
import com.softserve.kolisnyk.model.UserAuthToken;
import com.sun.istack.internal.NotNull;

public interface AuthService {

  void login(@NotNull String email,@NotNull String password, boolean keepInSystem) throws NoCorrectLoginData,FailedToLoginException;
  void logout() throws FailedToLogoutException;
  UserAuthToken findBySelector(String selector);
}
