package com.softserve.kolisnyk.controller;

import com.softserve.kolisnyk.exception.FailedToLogoutException;
import com.softserve.kolisnyk.service.AuthService;
import com.softserve.kolisnyk.service.impl.AuthServiceImpl;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/logout")
public class LogoutController extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    AuthService authService = new AuthServiceImpl(request.getSession(), request, response);
    try {
      authService.logout();
    } catch (FailedToLogoutException e) {
      e.printStackTrace();
    }

    RequestDispatcher rd = request.getRequestDispatcher("/");
    rd.forward(request, response);
  }
}
