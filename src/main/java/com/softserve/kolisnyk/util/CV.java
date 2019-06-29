package com.softserve.kolisnyk.util;

public interface CV {
     interface Role{
      String ADMIN="ADMIN";
      String USER="USER";
    }
    interface Database{
      String DRIVER = "com.mysql.cj.jdbc.Driver";
      String URL= "jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC";
      String USER_NAME="root";
      String USER_PASSWORD="root";
    }
}
