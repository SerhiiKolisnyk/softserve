package com.softserve.kolisnyk.service.impl;

import com.softserve.kolisnyk.controller.RegisterController;
import com.softserve.kolisnyk.dao.UserDAO;
import com.softserve.kolisnyk.dao.impl.UserDAOImpl;
import com.softserve.kolisnyk.model.User;
import com.softserve.kolisnyk.model.UserAuthToken;
import com.softserve.kolisnyk.service.UserService;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImpl implements UserService {

  private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

  private UserDAO userDAO;

  public UserServiceImpl() {
    userDAO = new UserDAOImpl();
  }


  @Override
  public void add(User user) {
    try {
      userDAO.addUser(user);
    } catch (SQLException e) {
      e.printStackTrace();
      LOGGER.error(e.getMessage());
    }
  }

  @Override
  public void save(UserAuthToken userAuthToken) {
    try {
      userDAO.addUserUserAuthToken(userAuthToken);
    } catch (SQLException e) {
      e.printStackTrace();
      LOGGER.error(e.getMessage());
    }
  }

  @Override
  public User getById(int id) {
    return null;
  }

  @Override
  public void update(User user) {

  }

  @Override
  public void delete(Integer id) {

  }

  @Override
  public User getByEmail(String email) {
    try {
      return userDAO.getUserByEmail(email);
    } catch (SQLException e) {
      LOGGER.error(e.getMessage());
    }
    return null;
  }
}
