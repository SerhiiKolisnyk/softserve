package com.softserve.kolisnyk.dao.impl;

import com.softserve.kolisnyk.dao.UserDAO;
import com.softserve.kolisnyk.model.User;
import com.softserve.kolisnyk.model.UserAuthToken;
import com.softserve.kolisnyk.util.DBConection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAOImpl implements UserDAO {

  private static final String ID = "idUser";
  private static final String NAME = "name";
  private static final String SURNAME = "surname";
  private static final String EMAIL = "email";
  private static final String ADDRESS = "adrress";
  private static final String ROLE = "role";

  @Override
  public User getUserByCredentials(String email, String password) throws SQLException {
   return null;
  }

  @Override
  public void addUserUserAuthToken(UserAuthToken userAuthToken) throws SQLException {
    final String sql="INSERT INTO userauthtoken(selector,validator,user_id) VALUES"
        + " (?,?,?);";
    Connection connection = DBConection.getConnection();
    PreparedStatement stmt = connection.prepareStatement(sql);
    stmt.setString(1,userAuthToken.getSelector());
    stmt.setString(2,userAuthToken.getValidator());
    stmt.setInt(3,userAuthToken.getUser().getId());
    stmt.executeUpdate();
    stmt.close();
  }

  @Override
  public void addUser(User user) throws SQLException {
    final String sql="INSERT INTO user(name,surname,email,adrress,role,password) VALUES"
      + " (?,?,?,?,?,?);";
    Connection connection = DBConection.getConnection();
    System.out.println("cc"+connection);
    PreparedStatement stmt = connection.prepareStatement(sql);
    System.out.println("stmt"+stmt);
    stmt.setString(1,user.getName());
    stmt.setString(2,user.getSurName());
    stmt.setString(3,user.getEmail());
    stmt.setString(4,user.getAddress());
    stmt.setString(5,user.getRole());
    stmt.setString(6,user.getPassword());
    stmt.executeUpdate();
    stmt.close();
  }

  @Override
  public User getUserByEmail(String email) throws SQLException {
    Connection connection = DBConection.getConnection();
    PreparedStatement stmt = connection.prepareStatement("SELECT user.idUser,user.name, user.surname, user.adrress,user.role,user.password FROM user WHERE user.email=?");
    stmt.setString(1, email);

    ResultSet rs = stmt.executeQuery();
    User user=null;
    if (rs.first()) {
      //Retrieve by column name
      int id = rs.getInt("idUser");
      String name = rs.getString("name");
      String surName = rs.getString("surname");
      String adrress = rs.getString("adrress");
      String role = rs.getString("role");
      String password = rs.getString("password");
      user=new User(id,name,surName,email,password,adrress,role);
    }
    return user;
  }

  @Override
  public UserAuthToken findBySelector(String selector) throws SQLException {
    PreparedStatement stmt = null;
    Connection connection = DBConection.getConnection();
    stmt = connection.prepareStatement("SELECT * FROM user JOIN userauthtoken ON user.idUser = userauthtoken.user_id WHERE userauthtoken.selector =?");
    stmt.setString(1, selector);

    ResultSet rs = stmt.executeQuery();
    UserAuthToken userAuthToken=null;
    if (rs.first()) {
      //Retrieve by column name
      int id = rs.getInt("idUser");
      String name = rs.getString("name");
      String surName = rs.getString("surname");
      String email = rs.getString("email");
      String adrress = rs.getString("adrress");
      String role = rs.getString("role");
      String validator = rs.getString("validator");
      int idToken = rs.getInt("idUserAuthToken");
      User user=new User(id,name,surName,email,adrress,role);
      userAuthToken=new UserAuthToken(idToken,selector,validator,user);
    }
    return userAuthToken;
  }

  @Override
  public void update(UserAuthToken userAuthToken) throws SQLException {
    final String sql="UPDATE userauthtoken SET  userauthtoken.selector=?, userauthtoken.validator=? WHERE userauthtoken.idUserAuthToken=? ";
    Connection connection = DBConection.getConnection();
    PreparedStatement stmt = connection.prepareStatement(sql);
    stmt.setString(1,userAuthToken.getSelector());
    stmt.setString(2,userAuthToken.getValidator());
    stmt.setInt(3,userAuthToken.getId());
    stmt.executeUpdate();
    stmt.close();
  }

  @Override
  public void delete(int userAuthTokenId) throws SQLException {
     final String sql = "DELETE FROM userauthtoken WHERE userauthtoken.idUserAuthToken=?";
    Connection connection = DBConection.getConnection();
    PreparedStatement stmt = connection.prepareStatement(sql);
    stmt.setInt(1,userAuthTokenId);
    stmt.executeUpdate();
    stmt.close();
  }
}
