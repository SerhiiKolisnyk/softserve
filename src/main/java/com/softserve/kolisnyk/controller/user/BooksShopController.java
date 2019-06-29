package com.softserve.kolisnyk.controller.user;

import com.softserve.kolisnyk.model.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/user/book-shop")
public class BooksShopController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    List<User> users=new ArrayList<>();
    users.add(new User("Bunyan ","The Pilgrim’s Progress","1678",null,null));
    users.add(new User("Defoe "," Robinson Crusoe","1719",null,null));
    users.add(new User("Swift","Gulliver’s Travels","1726",null,null));
    users.add(new User(" Richardson","Clarissa","1784",null,null));

    req.setAttribute("list", users);
    req.setAttribute("name", "petro");

    req.getRequestDispatcher("/user/books-shop.jsp").forward(req, resp);
  }
}
