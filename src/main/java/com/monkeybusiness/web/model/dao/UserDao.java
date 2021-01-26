package com.monkeybusiness.web.model.dao;

import com.monkeybusiness.web.exception.DaoException;
import com.monkeybusiness.web.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class UserDao extends AbstractDao<User> {
  private final String FIND_ALL = "SELECT user_id, nickname, email, password, role FROM users";
  private final String CHECK_USER_EXISTANCE = "SELECT TRUE FROM users WHERE (nickname LIKE ? OR email LIKE ?) LIMIT 1";
  private final String FIND_USER = "SELECT user_id, nickname, email, user_role FROM users " +
          "WHERE (nickname LIKE ? OR email LIKE ?) AND password LIKE ? LIMIT 1";
  private final String CREATE_USER = "INSERT INTO users (nickname, email, password, user_role) VALUES (?, ?, ?, ?) ";

  @Override
  public List<User> findAll() throws DaoException {
    return null;
  }

  @Override
  public User findEntityById(long id) throws DaoException {
    return null;
  }

  @Override
  public boolean exists(User entity) throws DaoException {
    PreparedStatement statement = null;
    boolean exists = false;
    try {
      statement = connection.prepareStatement(CHECK_USER_EXISTANCE);
      statement.setString(1, entity.getNickname());
      statement.setString(2, entity.getEmail());
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        exists = resultSet.getBoolean(1);
      }
    } catch (SQLException throwables) {
      throw new DaoException(throwables);
    } finally {
      close(statement);
    }
    return exists;
  }

  public boolean login(User entity, String password) throws DaoException {
    PreparedStatement statement = null;
    boolean logined = false;
    try {
      statement = connection.prepareStatement(FIND_USER);
      statement.setString(1, entity.getNickname());
      statement.setString(2, entity.getEmail());
      statement.setString(3, password);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        logined = true;
        entity.setUserId(resultSet.getLong(1));
        entity.setNickname(resultSet.getString(2));
        entity.setEmail(resultSet.getString(3));
        entity.setRole(User.Role.valueOf(resultSet.getString(4).toUpperCase(Locale.ROOT)));
      }
    } catch (SQLException throwables) {
      throw new DaoException(throwables);
    } finally {
      close(statement);
    }
    return logined;
  }

  @Override
  public boolean delete(long id) throws DaoException {
    return false;
  }

  @Override
  public boolean create(User entity, String... params) throws DaoException {
    PreparedStatement statement = null;
    boolean created = false;
    if (!exists(entity)) {
      try {
        statement = connection.prepareStatement(CREATE_USER);
        statement.setString(1, entity.getNickname());
        statement.setString(2, entity.getEmail());
        statement.setString(3, params[0]);
        statement.setString(4, entity.getRole().name().toLowerCase(Locale.ROOT));
        statement.execute();
        created = true;
      } catch (SQLException throwables) {
        throw new DaoException(throwables);
      } finally {
        close(statement);
      }
    }
    return created;
  }

  @Override
  public User update(User entity) throws DaoException {
    return null;
  }
}
