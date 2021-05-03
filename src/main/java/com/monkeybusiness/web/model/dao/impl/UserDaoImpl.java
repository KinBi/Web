package com.monkeybusiness.web.model.dao.impl;

import com.monkeybusiness.web.exception.DaoException;
import com.monkeybusiness.web.model.dao.UserDao;
import com.monkeybusiness.web.model.entity.User;
import com.monkeybusiness.web.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public enum UserDaoImpl implements UserDao {
  INSTANCE;

  private static final Logger LOGGER = LogManager.getLogger();
  private static final String SQL_CREATE_USER = "INSERT INTO users (nickname, email, password, user_role) VALUES (?, ?, ?, ?) ";
  private static final String SQL_FIND_ALL = "SELECT user_id, nickname, email, score, cash, profile_image, enabled, user_role FROM users";
  private static final String SQL_FIND_IN_RANGE = "SELECT user_id, nickname, email, score, cash, profile_image, enabled, user_role " +
          "FROM users LIMIT ? OFFSET ?";
  private static final String SQL_FIND_USER_BY_ID = "SELECT user_id, nickname, email, score, cash, profile_image, enabled, user_role " +
          "FROM users WHERE user_id = ?";
  private static final String SQL_FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT user_id, nickname, email, score, cash, " +
          "profile_image, user_role, enabled FROM users WHERE (nickname LIKE ? OR email LIKE ?) AND password LIKE ? LIMIT 1";
  private static final String SQL_CALCULATE_COUNT = "SELECT COUNT(*) FROM users";
  private static final String SQL_UPDATE_USER_NICKNAME = "UPDATE users SET nickname = ? WHERE user_id = ?";
  private static final String SQL_UPDATE_USER_EMAIL = "UPDATE users SET email = ? WHERE user_id = ?";
  private static final String SQL_UPDATE_USER_PASSWORD = "UPDATE users SET password = ? WHERE user_id = ?";
  private static final String SQL_UPDATE_USER_SCORE = "UPDATE users SET score = ? WHERE user_id = ?";
  private static final String SQL_UPDATE_USER_CASH = "UPDATE users SET cash = ? WHERE user_id = ?";
  private static final String SQL_UPDATE_USER_PROFILE_IMAGE = "UPDATE users SET profile_image = ? WHERE user_id = ?";
  private static final String SQL_UPDATE_USER_ENABLING = "UPDATE users SET enabled = ? WHERE user_id = ?";
  private static final String SQL_UPDATE_USER_ROLE = "UPDATE users SET user_role = ? WHERE user_id = ?";
  private static final String SQL_UPDATE_USER = "UPDATE users SET nickname = ?, email = ?, score = ?, cash = ?," +
          "profile_image = ?, enabled = ?, user_role = ? WHERE user_id = ?";
  private static final String SQL_DELETE_USER = "DELETE FROM users WHERE user_id = ?";
  private static final String SQL_CHECK_USER_EXISTANCE = "SELECT user_id FROM users " +
          "WHERE (nickname LIKE ? OR email LIKE ?) LIMIT 1";

  @Override
  public boolean create(User entity, String... params) throws DaoException {
    boolean created = false;
    if (!exists(entity)) {
      try (Connection connection = ConnectionPool.INSTANCE.getConnection();
           PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
        statement.setString(1, entity.getNickname());
        statement.setString(2, entity.getEmail());
        statement.setString(3, params[0]);
        statement.setString(4, entity.getRole().name().toLowerCase(Locale.ROOT));
        statement.executeUpdate();
        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
          entity.setUserId(resultSet.getLong(1));
        }
        created = true;
      } catch (SQLException throwables) {
        LOGGER.log(Level.ERROR, throwables);
        throw new DaoException(throwables);
      }
    }
    return created;
  }

  @Override
  public boolean login(User user, String password) throws DaoException {
    boolean logined = false;
    try (Connection connection = ConnectionPool.INSTANCE.getConnection();
         PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_AND_PASSWORD)) {
      statement.setString(1, user.getNickname());
      statement.setString(2, user.getEmail());
      statement.setString(3, password);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        if (resultSet.getBoolean(8)) {
          logined = true;
          user.setUserId(resultSet.getLong(1));
          user.setNickname(resultSet.getString(2));
          user.setEmail(resultSet.getString(3));
          user.setScore(resultSet.getLong(4));
          user.setCash(resultSet.getDouble(5));
          user.setImage(resultSet.getString(6));
          user.setRole(User.Role.valueOf(resultSet.getString(7).toUpperCase(Locale.ROOT)));
        } else {
          user.setEnabled(false);
        }
      }
    } catch (SQLException throwables) {
      LOGGER.log(Level.ERROR, throwables);
      throw new DaoException(throwables);
    }
    return logined;
  }

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
        String image = resultSet.getString(6);
        boolean enabled = resultSet.getBoolean(7);
        User.Role role = User.Role.valueOf(resultSet.getString(8).toUpperCase(Locale.ROOT));
        User user = new User(id, nickname, email, score, cash, image, enabled, role);
        userList.add(user);
      }
    } catch (SQLException throwables) {
      LOGGER.log(Level.ERROR, throwables);
      throw new DaoException(throwables);
    }
    return userList;
  }

  @Override
  public List<User> findInRange(long limit, long offset) throws DaoException {
    List<User> userList = new ArrayList<>();
    try (Connection connection = ConnectionPool.INSTANCE.getConnection();
         PreparedStatement statement = connection.prepareStatement(SQL_FIND_IN_RANGE)) {
      statement.setLong(1, limit);
      statement.setLong(2, offset);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        long id = resultSet.getLong(1);
        String nickname = resultSet.getString(2);
        String email = resultSet.getString(3);
        long score = resultSet.getLong(4);
        double cash = resultSet.getDouble(5);
        String image = resultSet.getString(6);
        boolean enabled = resultSet.getBoolean(7);
        User.Role role = User.Role.valueOf(resultSet.getString(8).toUpperCase(Locale.ROOT));
        User user = new User(id, nickname, email, score, cash, image, enabled, role);
        userList.add(user);
      }
    } catch (SQLException throwables) {
      LOGGER.log(Level.ERROR, throwables);
      throw new DaoException(throwables);
    }
    return userList;
  }

  @Override
  public User findEntityById(long id) throws DaoException {
    User user = null;
    try (Connection connection = ConnectionPool.INSTANCE.getConnection();
         PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        String nickname = resultSet.getString(2);
        String email = resultSet.getString(3);
        long score = resultSet.getLong(4);
        double cash = resultSet.getDouble(5);
        String image = resultSet.getString(6);
        boolean enabled = resultSet.getBoolean(7);
        User.Role role = User.Role.valueOf(resultSet.getString(8).toUpperCase(Locale.ROOT));
        user = new User(id, nickname, email, score, cash, image, enabled, role);
      }
    } catch (SQLException throwables) {
      LOGGER.log(Level.ERROR, throwables);
      throw new DaoException(throwables);
    }
    return user;
  }

  @Override
  public long calculateCount() throws DaoException {
    long count = 0;
    try (Connection connection = ConnectionPool.INSTANCE.getConnection();
         PreparedStatement statement = connection.prepareStatement(SQL_CALCULATE_COUNT)) {
      ResultSet resultSet = statement.executeQuery();
      if (resultSet.next()) {
        count = resultSet.getLong(1);
      }
    } catch (SQLException throwables) {
      LOGGER.log(Level.ERROR, throwables);
      throw new DaoException(throwables);
    }
    return count;
  }

  @Override
  public boolean updateNickname(String nickname, long userId) throws DaoException {
    boolean updated;
    try (Connection connection = ConnectionPool.INSTANCE.getConnection();
         PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_NICKNAME)) {
      statement.setString(1, nickname);
      statement.setLong(2, userId);
      updated = statement.executeUpdate() > 0;
    } catch (SQLException throwables) {
      LOGGER.log(Level.ERROR, throwables);
      throw new DaoException(throwables);
    }
    return updated;
  }

  @Override
  public boolean updateEmail(String email, long userId) throws DaoException {
    boolean updated;
    try (Connection connection = ConnectionPool.INSTANCE.getConnection();
         PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_EMAIL)) {
      statement.setString(1, email);
      statement.setLong(2, userId);
      updated = statement.executeUpdate() > 0;
    } catch (SQLException throwables) {
      LOGGER.log(Level.ERROR, throwables);
      throw new DaoException(throwables);
    }
    return updated;
  }

  @Override
  public boolean updatePassword(String password, long userId) throws DaoException {
    boolean updated;
    try (Connection connection = ConnectionPool.INSTANCE.getConnection();
         PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_PASSWORD)) {
      statement.setString(1, password);
      statement.setLong(2, userId);
      updated = statement.executeUpdate() > 0;
    } catch (SQLException throwables) {
      LOGGER.log(Level.ERROR, throwables);
      throw new DaoException(throwables);
    }
    return updated;
  }

  @Override
  public boolean updateScore(long score, long userId) throws DaoException {
    boolean updated;
    try (Connection connection = ConnectionPool.INSTANCE.getConnection();
         PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_SCORE)) {
      statement.setLong(1, score);
      statement.setLong(2, userId);
      updated = statement.executeUpdate() > 0;
    } catch (SQLException throwables) {
      LOGGER.log(Level.ERROR, throwables);
      throw new DaoException(throwables);
    }
    return updated;
  }

  @Override
  public boolean updateCash(double cash, long userId) throws DaoException {
    boolean updated;
    try (Connection connection = ConnectionPool.INSTANCE.getConnection();
         PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_CASH)) {
      statement.setDouble(1, cash);
      statement.setLong(2, userId);
      updated = statement.executeUpdate() > 0;
    } catch (SQLException throwables) {
      LOGGER.log(Level.ERROR, throwables);
      throw new DaoException(throwables);
    }
    return updated;
  }

  @Override
  public boolean updateImage(String image, long userId) throws DaoException {
    boolean updated;
    try (Connection connection = ConnectionPool.INSTANCE.getConnection();
         PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_PROFILE_IMAGE)) {
      statement.setString(1, image);
      statement.setLong(2, userId);
      updated = statement.executeUpdate() > 0;
    } catch (SQLException throwables) {
      LOGGER.log(Level.ERROR, throwables);
      throw new DaoException(throwables);
    }
    return updated;
  }

  @Override
  public boolean updateEnabling(boolean enabled, long userId) throws DaoException {
    boolean updated;
    try (Connection connection = ConnectionPool.INSTANCE.getConnection();
         PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_ENABLING)) {
      statement.setBoolean(1, enabled);
      statement.setLong(2, userId);
      updated = statement.executeUpdate() > 0;
    } catch (SQLException throwables) {
      LOGGER.log(Level.ERROR, throwables);
      throw new DaoException(throwables);
    }
    return updated;
  }

  @Override
  public boolean updateRole(User.Role role, long userId) throws DaoException {
    boolean updated;
    try (Connection connection = ConnectionPool.INSTANCE.getConnection();
         PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_ROLE)) {
      statement.setString(1, role.name().toLowerCase(Locale.ROOT));
      statement.setLong(2, userId);
      updated = statement.executeUpdate() > 0;
    } catch (SQLException throwables) {
      LOGGER.log(Level.ERROR, throwables);
      throw new DaoException(throwables);
    }
    return updated;
  }

  @Override
  public boolean update(User entity) throws DaoException {
    boolean updated;
    try (Connection connection = ConnectionPool.INSTANCE.getConnection();
         PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {
      statement.setString(1, entity.getNickname());
      statement.setString(2, entity.getEmail());
      statement.setLong(3, entity.getScore());
      statement.setDouble(4, entity.getCash());
      statement.setString(5, entity.getImage());
      statement.setBoolean(6, entity.isEnabled());
      statement.setString(7, entity.getRole().name().toLowerCase(Locale.ROOT));
      statement.setLong(8, entity.getUserId());
      updated = statement.executeUpdate() > 0;
    } catch (SQLException throwables) {
      LOGGER.log(Level.ERROR, throwables);
      throw new DaoException(throwables);
    }
    return updated;
  }

  @Override
  public boolean delete(long id) throws DaoException {
    boolean deleted;
    try (Connection connection = ConnectionPool.INSTANCE.getConnection();
         PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER)) {
      statement.setLong(1, id);
      deleted = statement.executeUpdate() > 0;
    } catch (SQLException throwables) {
      LOGGER.log(Level.ERROR, throwables);
      throw new DaoException(throwables);
    }
    return deleted;
  }

  @Override
  public boolean exists(User entity) throws DaoException {
    boolean exists;
    try (Connection connection = ConnectionPool.INSTANCE.getConnection();
         PreparedStatement statement = connection.prepareStatement(SQL_CHECK_USER_EXISTANCE)) {
      statement.setString(1, entity.getNickname());
      statement.setString(2, entity.getEmail());
      ResultSet resultSet = statement.executeQuery();
      exists = resultSet.next();
    } catch (SQLException throwables) {
      throw new DaoException(throwables);
    }
    return exists;
  }
}
