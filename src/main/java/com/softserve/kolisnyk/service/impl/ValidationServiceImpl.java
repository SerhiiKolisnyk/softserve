package com.softserve.kolisnyk.service.impl;

import com.softserve.kolisnyk.dao.UserDAO;
import com.softserve.kolisnyk.dao.impl.UserDAOImpl;
import com.softserve.kolisnyk.service.ValidationService;
import com.sun.istack.internal.NotNull;
import java.sql.SQLException;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationServiceImpl implements ValidationService {

  private static final Logger logger = LoggerFactory.getLogger(ValidationServiceImpl.class);

  private UserDAO userDAO;

  public ValidationServiceImpl() {
    userDAO = new UserDAOImpl();
  }

  @Override
  public boolean isEmailRegistered(@NotNull String email) {
    try {
      if (userDAO.getUserByEmail(email) != null) {
        return true;
      }
    } catch (SQLException e) {
      logger.error(e.getMessage());
    }
    return false;
  }

  @Override
  public boolean isValidEmail(String email) {
    String emailRegex = ".+@.+";

    Pattern pat = Pattern.compile(emailRegex);
    if (email == null) {
      return false;
    }
    return pat.matcher(email).matches();
  }
}
