package com.softserve.kolisnyk.service;

import com.sun.istack.internal.NotNull;

public interface ValidationService {
  boolean isEmailRegistered(@NotNull String email);
  boolean isValidEmail(@NotNull String email);
}
