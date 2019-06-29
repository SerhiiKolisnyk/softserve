package com.softserve.kolisnyk.filter;

import com.softserve.kolisnyk.controller.RegisterController;
import com.softserve.kolisnyk.dao.UserDAO;
import com.softserve.kolisnyk.dao.impl.UserDAOImpl;
import com.softserve.kolisnyk.model.UserAuthToken;
import com.softserve.kolisnyk.service.impl.EncryptServiceImpl;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(urlPatterns = {
    "/login",
    "/register"
})
public class AuthenticationFilter implements Filter {
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    HttpSession session = httpRequest.getSession(false);

    boolean loggedIn = session != null && session.getAttribute("loggedUser") != null;

    Cookie[] cookies = httpRequest.getCookies();

    if (!loggedIn && cookies != null) {
      // process auto login for remember me feature
      String selector = "";
      String rawValidator = "";

      for (Cookie aCookie : cookies) {
        if (aCookie.getName().equals("selector")) {
          selector = aCookie.getValue();
        } else if (aCookie.getName().equals("validator")) {
          rawValidator = aCookie.getValue();
        }
      }

      if (!"".equals(selector) && !"".equals(rawValidator)) {
        UserDAO authDAO = new UserDAOImpl();

        //CustomerAuthToken token = authDAO.findBySelector(selector);
        UserAuthToken token = null;
        try {
          token = authDAO.findBySelector(selector);
        } catch (SQLException e) {
          e.printStackTrace();
          return;
        }

        if (token != null) {
          String hashedValidatorDatabase = token.getValidator();
          String hashedValidatorCookie = new EncryptServiceImpl().hashRememberMeToken(rawValidator);

          if (hashedValidatorCookie.equals(hashedValidatorDatabase)) {
            session = httpRequest.getSession();
            session.setAttribute("loggedUser", token.getUser());
            loggedIn = true;

            // update new token in database
            String newSelector = RandomStringUtils.randomAlphanumeric(12);
            String newRawValidator =  RandomStringUtils.randomAlphanumeric(64);

            String newHashedValidator = new EncryptServiceImpl().hashRememberMeToken(newRawValidator);

            token.setSelector(newSelector);
            token.setValidator(newHashedValidator);
            try {
              authDAO.update(token);
            } catch (SQLException e) {
              e.printStackTrace();
              return;
            }

            // update cookie
            Cookie cookieSelector = new Cookie("selector", newSelector);
            cookieSelector.setMaxAge(604800);

            Cookie cookieValidator = new Cookie("validator", newRawValidator);
            cookieValidator.setMaxAge(604800);

            httpResponse.addCookie(cookieSelector);
            httpResponse.addCookie(cookieValidator);

          }
        }
      }

    }
    chain.doFilter(request, response);

  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void destroy() {

  }
}
