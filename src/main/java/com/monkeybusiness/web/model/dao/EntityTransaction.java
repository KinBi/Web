package com.monkeybusiness.web.model.dao;

import com.monkeybusiness.web.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
// fixme
public class EntityTransaction {
  private static final Logger LOGGER = LogManager.getLogger();
  private Connection connection;

  public void begin(AbstractDao dao, AbstractDao... daos) {
    if (connection == null) {
      connection = ConnectionPool.INSTANCE.getConnection();
    }
    try {
      connection.setAutoCommit(false);
    } catch (SQLException throwables) {
      LOGGER.log(Level.ERROR, throwables);
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
      LOGGER.log(Level.ERROR, throwables);
    }
    connection = null;
  }

  public void commit() {
    try {
      connection.commit();
    } catch (SQLException throwables) {
      LOGGER.log(Level.ERROR, throwables);
    }
  }

  public void rollback() {
    try {
      connection.rollback();
    } catch (SQLException throwables) {
      LOGGER.log(Level.ERROR, throwables);
    }
  }
}
