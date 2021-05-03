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
  private static final Properties properties = new Properties();
  private static final String DBCONFIG_PATH = "/config/database.properties";
  private static final String DRIVER_ATTRIBUTE = "db.driver";
  private static final String URL_ATTRIBUTE = "db.url";
  private static final String DATABASE_URL;

  static {
    try (InputStream is = ConnectionCreator.class.getResourceAsStream(DBCONFIG_PATH)) {
      properties.load(is);
      Class.forName(properties.getProperty(DRIVER_ATTRIBUTE));
    } catch (IOException | ClassNotFoundException e) {
      LOGGER.log(Level.FATAL, e);
    }
    DATABASE_URL = properties.getProperty(URL_ATTRIBUTE);
  }

  private ConnectionCreator() {}

  public static Connection createConnection() throws SQLException {
    Connection connection = DriverManager.getConnection(DATABASE_URL, properties);
    return connection;
  }
}
