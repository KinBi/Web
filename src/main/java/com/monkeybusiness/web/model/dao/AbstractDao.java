package com.monkeybusiness.web.model.dao;

import com.monkeybusiness.web.exception.DaoException;
import com.monkeybusiness.web.model.entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDao <T extends Entity> {
  protected Connection connection;

  public abstract List<T> findAll() throws DaoException;
  public abstract T findEntityById(long id) throws DaoException;
  public abstract boolean exists(T entity) throws DaoException;
  public abstract boolean delete(long id) throws DaoException;
  public abstract boolean create(T entity, String... params) throws DaoException;
  public abstract T update(T entity) throws DaoException;

  public void close(Statement statement) {
    try {
      if (statement == null) {
        statement.close();
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  void setConnection(Connection connection) {
    this.connection = connection;
  }
}
