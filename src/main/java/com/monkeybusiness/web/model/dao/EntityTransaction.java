package com.monkeybusiness.web.model.dao;

import java.sql.*;

public class EntityTransaction {
  public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
  public static final String PROTOCOL_NAME = "jdbc";
  public static final String SUBPROTOCOL_NAME = "mysql";
  public static final String HOST = "//localhost";
  public static final String PORT = "3306";
  public static final String PATH = "/project_db";
  public static final String COLON = ":";
  private Connection connection;

  public void begin(AbstractDao dao, AbstractDao... daos) {
    if (connection == null) {
      try {
        Class.forName(DRIVER_NAME);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      String user = "admin";
      String password = "admin";

      String url = PROTOCOL_NAME + COLON + SUBPROTOCOL_NAME + COLON + HOST +
              COLON + PORT + PATH;
      try {
        connection = DriverManager.getConnection(url, user, password);
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    try {
      connection.setAutoCommit(false);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    dao.setConnection(connection);
    for (AbstractDao daoElement : daos) {
      daoElement.setConnection(connection);
    }
  }

  public void end() {
    try {
      connection.setAutoCommit(true);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    connection = null;
  }

  public void commit() {
    try {
      connection.commit();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public void rollback() {
    try {
      connection.rollback();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
