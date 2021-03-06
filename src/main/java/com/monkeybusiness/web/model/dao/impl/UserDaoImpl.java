package com.monkeybusiness.web.model.dao.impl;

import com.monkeybusiness.web.exception.DaoException;
import com.monkeybusiness.web.model.dao.UserDao;
import com.monkeybusiness.web.model.entity.User;
import com.monkeybusiness.web.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public enum UserDaoImpl implements UserDao {
  INSTANCE;

  private static final String SQL_FIND_ALL = "SELECT user_id, nickname, email, score, cash, user_role FROM users";
  private static final String SQL_CHECK_USER_EXISTANCE = "SELECT TRUE FROM users WHERE (nickname LIKE ? OR email LIKE ?) LIMIT 1";
  private static final String SQL_FIND_USER = "SELECT user_id, nickname, email, score, cash, user_role, enabled FROM users " +
          "WHERE (nickname LIKE ? OR email LIKE ?) AND password LIKE ? LIMIT 1";
  private static final String SQL_CREATE_USER = "INSERT INTO users (nickname, email, password, user_role) VALUES (?, ?, ?, ?) ";

  @Override
  public List<User> findAll() throws DaoException {
    List<User> userList = new ArrayList<>();
    try (Connection connection = ConnectionPool.INSTANCE.getConnection();
         PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)) {
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        long id = resultSet.getLong(1);
        String nickname = resultSet.getString(2);
        String email = resultSet.getString(3);
        long score = resultSet.getLong(4);
        double cash = resultSet.getDouble(5);
        User.Role role = User.Role.valueOf(resultSet.getString(6).toUpperCase(Locale.ROOT));
        User user = new User(id, nickname, email, score, cash, role);
        userList.add(user);
      }
    } catch (SQLException throwables) {
      throw new DaoException(throwables);
    }
    return userList;
  }

  @Override
  public User findEntityById(long id) throws DaoException {
    return null; // todo
  }

  @Override
  public boolean exists(User entity) throws DaoException {
    boolean exists = false;
    try (Connection connection = ConnectionPool.INSTANCE.getConnection();
         PreparedStatement statement = connection.prepareStatement(SQL_CHECK_USER_EXISTANCE)) {
      statement.setString(1, entity.getNickname());
      statement.setString(2, entity.getEmail());
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        exists = resultSet.getBoolean(1);
      }
    } catch (SQLException throwables) {
      throw new DaoException(throwables);
    }
    return exists;
  }

  @Override
  public boolean login(User entity, String password) throws DaoException {
    boolean logined = false;
    try (Connection connection = ConnectionPool.INSTANCE.getConnection();
         PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER)) {
      statement.setString(1, entity.getNickname());
      statement.setString(2, entity.getEmail());
      statement.setString(3, password);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        if (resultSet.getBoolean(7)) {
          logined = true;
          entity.setUserId(resultSet.getLong(1));
          entity.setNickname(resultSet.getString(2));
          entity.setEmail(resultSet.getString(3));
          entity.setScore(resultSet.getLong(4));
          entity.setCash(resultSet.getDouble(5));
          entity.setRole(User.Role.valueOf(resultSet.getString(6).toUpperCase(Locale.ROOT)));
        }
      }
    } catch (SQLException throwables) {
      throw new DaoException(throwables);
    }
    return logined;
  }

  @Override
  public boolean delete(long id) throws DaoException {
    return false; // todo
  }

  @Override
  public boolean create(User entity, String... params) throws DaoException {
    boolean created = false;
    if (!exists(entity)) {
      try (Connection connection = ConnectionPool.INSTANCE.getConnection();
           PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER)) {
        statement.setString(1, entity.getNickname());
        statement.setString(2, entity.getEmail());
        statement.setString(3, params[0]);
        statement.setString(4, entity.getRole().name().toLowerCase(Locale.ROOT));
        statement.execute();
        created = true;
      } catch (SQLException throwables) {
        throw new DaoException(throwables);
      }
    }
    return created;
  }

  @Override
  public User update(User entity) throws DaoException {
    return null; // todo
  }
}
