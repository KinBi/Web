package com.monkeybusiness.web.model.dao;

import com.monkeybusiness.web.exception.DaoException;
import com.monkeybusiness.web.model.entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface Dao<T extends Entity> {
  List<T> findAll() throws DaoException;
  T findEntityById(long id) throws DaoException;
  boolean exists(T entity) throws DaoException;
  boolean delete(long id) throws DaoException;
  boolean create(T entity, String... params) throws DaoException;
  T update(T entity) throws DaoException;
}
