package com.softserve.kolisnyk.service.impl;

import com.softserve.kolisnyk.dao.UserDAO;
import com.softserve.kolisnyk.dao.impl.UserDAOImpl;
import com.softserve.kolisnyk.exception.FailedToLoginException;
import com.softserve.kolisnyk.exception.FailedToLogoutException;
import com.softserve.kolisnyk.exception.NoCorrectLoginData;
import com.softserve.kolisnyk.model.User;
import com.softserve.kolisnyk.model.UserAuthToken;
import com.softserve.kolisnyk.service.AuthService;
import com.softserve.kolisnyk.service.EncryptService;
import com.softserve.kolisnyk.service.UserService;
import java.sql.SQLException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;

public class AuthServiceImpl implements AuthService {

  private EncryptService encryptService;
  private UserService userService;

  private HttpSession session;
  private HttpServletRequest httpServletRequest;
  private HttpServletResponse httpServletResponse;

  public AuthServiceImpl(HttpSession session,
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) {
    this.session = session;
    this.httpServletRequest = httpServletRequest;
    this.httpServletResponse = httpServletResponse;

    encryptService = new EncryptServiceImpl();
    userService = new UserServiceImpl();
  }

  @Override
  public void login(String email, String password, boolean keepInSystem)
      throws NoCorrectLoginData, FailedToLoginException {
    User user = userService.getByEmail(email);
    if (user == null) {
      throw new NoCorrectLoginData("No correct email!");
    }
    if (!encryptService.checkPass(password, user.getPassword())) {
      throw new NoCorrectLoginData("No correct password!");
    }

    session.setAttribute("loggedUser", user);
    if (keepInSystem) {
      UserAuthToken userAuthToke = new UserAuthToken();
      String selector = RandomStringUtils.randomAlphanumeric(12);
      String rawValidator = RandomStringUtils.randomAlphanumeric(13);
      String hashedValidator = encryptService.hashRememberMeToken(rawValidator);
      userAuthToke.setSelector(selector);
      userAuthToke.setValidator(hashedValidator);
      userAuthToke.setUser(user);
      userService.save(userAuthToke);
      Cookie cookieSelector = new Cookie("selector", selector);
      cookieSelector.setMaxAge(604800);

      Cookie cookieValidator = new Cookie("validator", rawValidator);
      cookieValidator.setMaxAge(604800);

      httpServletResponse.addCookie(cookieSelector);
      httpServletResponse.addCookie(cookieValidator);
    }
  }

  @Override
  public void logout() throws FailedToLogoutException {
    session.removeAttribute("loggedUser");

    Cookie[] cookies = httpServletRequest.getCookies();

    if (cookies != null) {
      String selector = "";

      for (Cookie aCookie : cookies) {
        if (aCookie.getName().equals("selector")) {
          selector = aCookie.getValue();
        }
      }

      if (!selector.isEmpty()) {
        // delete token from database
        UserDAO authDao = new UserDAOImpl();
        UserAuthToken token = null;
        try {
          token = authDao.findBySelector(selector);
        } catch (SQLException e) {
          e.printStackTrace();
          throw new FailedToLogoutException();
        }

        if (token != null) {
          try {
            authDao.delete(token.getId());
          } catch (SQLException e) {
            e.printStackTrace();
            throw new FailedToLogoutException();
          }

          Cookie cookieSelector = new Cookie("selector", "");
          cookieSelector.setMaxAge(0);

          Cookie cookieValidator = new Cookie("validator", "");
          cookieValidator.setMaxAge(0);
          httpServletResponse.addCookie(cookieSelector);
          httpServletResponse.addCookie(cookieValidator);
        }
      }
    }
  }

  @Override
  public UserAuthToken findBySelector(String selector) {
    return null;
  }
}
