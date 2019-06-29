package com.softserve.kolisnyk.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConection {

  private static volatile HikariDataSource hikariDataSource;

  public static Connection getConnection() throws SQLException {
    HikariDataSource localInstance = hikariDataSource;
    if (localInstance == null) {
      synchronized (DBConection.class) {
        localInstance = hikariDataSource;
        if (localInstance == null) {
          try {
            Class.forName(CV.Database.DRIVER);
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(CV.Database.URL);
            config.setUsername(CV.Database.USER_NAME);
            config.setPassword(CV.Database.USER_PASSWORD);
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            localInstance = hikariDataSource = new HikariDataSource(config);
          } catch (ClassNotFoundException e) {
            throw new SQLException(e);
          }
        }
      }
    }
    return localInstance.getConnection();
  }

  private DBConection() {
  }


}