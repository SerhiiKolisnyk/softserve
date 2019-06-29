package com.softserve.kolisnyk.controller;

import com.softserve.kolisnyk.exception.FailedToLoginException;
import com.softserve.kolisnyk.model.User;
import com.softserve.kolisnyk.service.AuthService;
import com.softserve.kolisnyk.service.EncryptService;
import com.softserve.kolisnyk.service.UserService;
import com.softserve.kolisnyk.service.impl.AuthServiceImpl;
import com.softserve.kolisnyk.service.impl.EncryptServiceImpl;
import com.softserve.kolisnyk.service.impl.UserServiceImpl;
import com.softserve.kolisnyk.service.impl.ValidationServiceImpl;
import com.softserve.kolisnyk.util.CV.Role;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {

  private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

  private static final String NAME = "nName";
  private static final String SURNAME = "nSurname";
  private static final String ADRRESS = "nAddress";
  private static final String EMAIL = "nEmail";
  private static final String PASSWORD = "nPassword";
  private static final String MESSAGE = "message";
  private static final String KEEP_IN = "keepIn";

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String name =  request.getParameter(NAME);
    String surname =  request.getParameter(SURNAME);
    String adrress =  request.getParameter(ADRRESS);
    String email =  request.getParameter(EMAIL);
    String password =  request.getParameter(PASSWORD);
    String keep =  request.getParameter(KEEP_IN);
    if (name == null) {
      findError("Name is required!", request, response);
      return;
    }
    if (surname == null) {
      findError("Surname is required!", request, response);
      return;
    }
    if (adrress == null) {
      findError("Adrress is required!", request, response);
      return;
    }
    if (email == null) {
      findError("Email is required!", request, response);
      return;
    }
    if (password == null ) {
      findError("Password is required!", request, response);
      return;
    } else if (password.length()<6 ) {
      findError("Password is too short!", request, response);
      return;
    }

    ValidationServiceImpl validationService = new ValidationServiceImpl();
    if (!validationService.isValidEmail(email)) {
      findError("email is not valid!", request, response);
      return;
    }

    if (validationService.isEmailRegistered(email)) {
      findError("email has already been taken!", request, response);
      return;
    }

    EncryptService encryptService = new EncryptServiceImpl();
    UserService service = new UserServiceImpl();

    String passwordHash = encryptService.hashPassword(password);
    User user = new User(name, surname, email, passwordHash, adrress, Role.USER);
    service.add(user);

    AuthService authService=new AuthServiceImpl(request.getSession(true),request,response);
    try {
      boolean keepIn=false;
      if (keep!=null && keep.equalsIgnoreCase("on")) {
        authService.login(user.getEmail(), password, true);
        if (user.getRole().equalsIgnoreCase(Role.USER)){
          RequestDispatcher rd = request.getRequestDispatcher("/mybooks");
          rd.forward(request, response);
        }else {
          RequestDispatcher rd = request.getRequestDispatcher("/books");
          rd.forward(request,response);
        }
      }else {
        RequestDispatcher rd = request.getRequestDispatcher("/login");
        rd.forward(request,response);
      }


    } catch (FailedToLoginException e) {
      e.printStackTrace();
      //todo open error page
    }
  }

  private void findError(String errorString, HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute(MESSAGE, errorString);
    request.setAttribute(NAME, request.getParameter(NAME));
    request.setAttribute(SURNAME, request.getParameter(SURNAME));
    request.setAttribute(ADRRESS, request.getParameter(ADRRESS));
    request.setAttribute(EMAIL, request.getParameter(EMAIL));
    request.setAttribute(PASSWORD, request.getParameter(PASSWORD));
    request.getRequestDispatcher("register.jsp").forward(request, response);
  }
}
