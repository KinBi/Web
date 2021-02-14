package com.monkeybusiness.web.model.pool;

import com.monkeybusiness.web.exception.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {
  INSTANCE;

  private static final Logger LOGGER = LogManager.getLogger();
  private BlockingQueue<ProxyConnection> freeConnections;
  private Queue<ProxyConnection> givenAwayConnections;

  private static final int DEFAULT_POOL_SIZE = 8;

  ConnectionPool() {
    freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
    givenAwayConnections = new ArrayDeque<>(DEFAULT_POOL_SIZE);
    for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
      Connection connection = null;
      try {
        connection = ConnectionCreator.createConnection();
      } catch (SQLException throwables) {
        throw new RuntimeException("Connections hasn't been created", throwables);
      }
      ProxyConnection proxyConnection = new ProxyConnection(connection);
      freeConnections.offer(proxyConnection);
    }
  }

  public Connection getConnection() {
    ProxyConnection connection = null;
    try {
      connection = freeConnections.take();
      givenAwayConnections.offer(connection);
    } catch (InterruptedException e) {
      LOGGER.log(Level.ERROR, e);
    }
    return connection;
  }

  public void releaseConnection(Connection connection) throws ConnectionPoolException {
    if (connection.getClass() != ProxyConnection.class) {
      throw new ConnectionPoolException("Not proxy connection");
    }
    ProxyConnection proxyConnection = (ProxyConnection) connection;
    givenAwayConnections.remove(proxyConnection);
    freeConnections.offer(proxyConnection);
  }

  public void destroyPool() {
    for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
      try {
        freeConnections.take().reallyClose();
      } catch (InterruptedException e) {
        LOGGER.log(Level.ERROR, e);
      } catch (SQLException throwables) {
        LOGGER.log(Level.ERROR, throwables);
      }
    }
    deregisterDrivers();
  }

  public void deregisterDrivers() {
    DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
      try {
        DriverManager.deregisterDriver(driver);
      } catch (SQLException throwables) {
        LOGGER.log(Level.ERROR, throwables);
      }
    });
  }
}
