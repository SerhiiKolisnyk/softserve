package com.softserve.kolisnyk.controller.admin;

import com.softserve.kolisnyk.model.User;
import com.softserve.kolisnyk.util.CV.Role;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/approved")
public class ApprovedController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<User> users=new ArrayList<>();
    users.add(new User("Name1","Surname1","@1","Lviv, add", Role.USER));
    users.add(new User("Name2","Surname6","@2","Lviv ss",Role.USER));
    users.add(new User("Name3","Surname7","@3","Kyiv ",Role.ADMIN));
    users.add(new User("Name4","Surname8","@4","str. Shevchenka",Role.ADMIN));
    users.add(new User("Name5","Surname8","@5","Adress",Role.USER));

    req.setAttribute("list", users);

    req.getRequestDispatcher("/admin/user-list.jsp").forward(req, resp);
  }
}
