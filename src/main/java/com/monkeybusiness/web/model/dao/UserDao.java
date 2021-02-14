package com.monkeybusiness.web.model.dao;

import com.monkeybusiness.web.exception.DaoException;
import com.monkeybusiness.web.model.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDao extends AbstractDao<User> {
  private static final String SQL_FIND_ALL = "SELECT user_id, nickname, email, user_role FROM users";
  private static final String SQL_CHECK_USER_EXISTANCE = "SELECT TRUE FROM users WHERE (nickname LIKE ? OR email LIKE ?) LIMIT 1";
  private static final String SQL_FIND_USER = "SELECT user_id, nickname, email, user_role FROM users " +
          "WHERE (nickname LIKE ? OR email LIKE ?) AND password LIKE ? LIMIT 1";
  private static final String SQL_CREATE_USER = "INSERT INTO users (nickname, email, password, user_role) VALUES (?, ?, ?, ?) ";

  @Override
  public List<User> findAll() throws DaoException {
    List<User> userList = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)) {
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        long id = resultSet.getLong(1);
        String nickname = resultSet.getString(2);
        String email = resultSet.getString(3);
        User.Role role = User.Role.valueOf(resultSet.getString(4).toUpperCase(Locale.ROOT));
        User user = new User(id, nickname, email, role);
        userList.add(user);
      }
    } catch (SQLException throwables) {
      throw new DaoException(throwables);
    }
    return userList; // todo
  }

  @Override
  public User findEntityById(long id) throws DaoException {
    return null; // todo
  }

  @Override
  public boolean exists(User entity) throws DaoException {
    boolean exists = false;
    try (PreparedStatement statement = connection.prepareStatement(SQL_CHECK_USER_EXISTANCE)){
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

  public boolean login(User entity, String password) throws DaoException {
    boolean logined = false;
    try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER)){
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
      try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER)) {
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
