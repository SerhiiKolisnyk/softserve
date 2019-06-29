package com.softserve.kolisnyk.controller;

import com.softserve.kolisnyk.exception.FailedToLoginException;
import com.softserve.kolisnyk.model.User;
import com.softserve.kolisnyk.service.AuthService;
import com.softserve.kolisnyk.service.impl.AuthServiceImpl;
import com.softserve.kolisnyk.util.CV.Role;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/login")
public class LoginContrloller extends HttpServlet {

  ;
  private static final String EMAIL = "nEmail";
  private static final String PASSWORD = "nPassword";
  private static final String MESSAGE = "message";
  private static final String REMEMBER = "nRememberMe";

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher rd = request.getRequestDispatcher("/login");
    rd.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String email = request.getParameter(EMAIL);
    String password = request.getParameter(PASSWORD);
    String remember = request.getParameter(REMEMBER);

    if (email == null) {
      findError("Email is required!", request, response);
      return;
    }
    if (password == null) {
      findError("Password is required!", request, response);
      return;
    }
    HttpSession session = request.getSession(true);
    AuthService authService = new AuthServiceImpl(session, request, response);
    try {
      boolean isRemember = false;
      if (remember != null && remember.equalsIgnoreCase("on")) {
        isRemember = true;
      }
      authService.login(email, password, isRemember);
    } catch (FailedToLoginException e) {
      e.printStackTrace();
      findError("No correct data", request, response);
      return;
    }
    User user = (User) session.getAttribute("loggedUser");
    if (user != null) {
      if (user.getRole().equalsIgnoreCase(Role.USER)) {
        response.sendRedirect("/user/book-shop");
      }else {
        response.sendRedirect("/ admin/users");
      }
    }
  }

  private void findError(String errorString, HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute(MESSAGE, errorString);
    request.setAttribute(EMAIL, request.getParameter(EMAIL));
    request.setAttribute(PASSWORD, request.getParameter(PASSWORD));
    request.getRequestDispatcher("index.jsp").forward(request, response);
  }
}
