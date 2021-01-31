package com.monkeybusiness.web.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final String DBCONFIG_PATH = "/config/config.properties";
  private static final Properties properties = new Properties();
  private static final String URL_ATTRIBUTE = "db.url";
  private static final String DRIVER_ATTRIBUTE = "db.driver";
  private static final String USER_ATTRIBUTE = "db.user";
  private static final String PASSWORD_ATTRIBUTE = "db.password";

  static {
    try (InputStream is = ConnectionCreator.class.getResourceAsStream(DBCONFIG_PATH)) {
      properties.load(is);
    } catch (IOException e) {
      LOGGER.log(Level.FATAL, e);
    }
  }

  public static Connection createConnection() throws SQLException {
    try {
      Class.forName(properties.getProperty(DRIVER_ATTRIBUTE));
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    String url = properties.getProperty(URL_ATTRIBUTE);
    String user = properties.getProperty(USER_ATTRIBUTE);
    String password = properties.getProperty(PASSWORD_ATTRIBUTE);
    Connection connection = DriverManager.getConnection(url, user, password);
    return connection;
  }
}
