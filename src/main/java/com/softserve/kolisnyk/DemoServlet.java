package com.softserve.kolisnyk;

import com.softserve.kolisnyk.util.DBConection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DemoServlet")
public class DemoServlet extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<h3>Hello World!</h3>");
  }
  public static void main(String[] args) {

    // establish DB url
//    String url = "jdbc.mysql://localhost:8800/mydb?useSSL=false&serverTimezone=UTC";

    try {
      // Establish a DB Connection Object
      Connection conn = DBConection.getConnection();
      // create a statement object to send to the db
      Statement statement = conn.createStatement();
      String sql = "SELECT * FROM author";
      // execute statement object by running
      // query and storing results in
      // a resultSet
      ResultSet resultSet = statement.executeQuery(sql);

      // process the result with a loop
      while (resultSet.next()){
        System.out.println(resultSet.getString("name"));
      }

    } catch (SQLException e) {
      System.out.println("Error connecting to DB: " + e.getMessage());
    }
  }

}