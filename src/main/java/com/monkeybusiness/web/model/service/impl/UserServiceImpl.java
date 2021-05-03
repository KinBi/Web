package com.monkeybusiness.web.model.service.impl;

import com.monkeybusiness.web.exception.DaoException;
import com.monkeybusiness.web.exception.UserServiceException;
import com.monkeybusiness.web.model.dao.UserDao;
import com.monkeybusiness.web.model.dao.impl.UserDaoImpl;
import com.monkeybusiness.web.model.entity.User;
import com.monkeybusiness.web.model.service.UserService;
import com.monkeybusiness.web.validator.UserDataValidator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public enum UserServiceImpl implements UserService {
  INSTANCE;

  private static final Logger LOGGER = LogManager.getLogger();
  private final UserDao userDao = UserDaoImpl.INSTANCE;

  @Override
  public Optional<User> register(String nickname, String email, String password) throws UserServiceException {
    if (!UserDataValidator.isUserValid(nickname, email, password)) {
      throw new UserServiceException();
    }
    Optional<User> registeredUser = Optional.empty();
    User user = new User(nickname, email);
    try {
      if (userDao.create(user, password)) {
        registeredUser = Optional.of(user);
      }
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e);
      throw new UserServiceException(e);
    }
    return registeredUser;
  }

  @Override
  public Optional<User> login(String login, String password) throws UserServiceException {
    if (!UserDataValidator.isLoginValid(login)) {
      throw new UserServiceException();
    }
    Optional<User> loginedUser = Optional.empty();
    User user = new User(login, login);
    try {
      if (userDao.login(user, password)) {
        loginedUser = Optional.of(user);
      }
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e);
      throw new UserServiceException(e);
    }
    return loginedUser;
  }

  @Override
  public boolean updateUserNickname(User user, String nickname) throws UserServiceException {
    if (!UserDataValidator.isNicknameValid(nickname)) {
      throw new UserServiceException();
    }
    boolean updated;
    try {
      updated = userDao.updateNickname(nickname, user.getUserId());
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e);
      throw new UserServiceException(e);
    }
    if (updated) {
      user.setNickname(nickname);
    }
    return updated;
  }

  @Override
  public boolean updateUserEmail(User user, String email) throws UserServiceException {
    if (!UserDataValidator.isEmailValid(email)) {
      throw new UserServiceException();
    }
    boolean updated;
    try {
      updated = userDao.updateEmail(email, user.getUserId());
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e);
      throw new UserServiceException(e);
    }
    if (updated) {
      user.setEmail(email);
    }
    return updated;
  }

  @Override
  public boolean updateUserPassword(User user, String password) throws UserServiceException {
    if (!UserDataValidator.isPasswordValid(password)) {
      throw new UserServiceException();
    }
    boolean updated;
    try {
      updated = userDao.updatePassword(password, user.getUserId());
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e);
      throw new UserServiceException(e);
    }
    return updated;
  }

  @Override
  public boolean updateUserScore(User user, Long score) throws UserServiceException {
    if (score < 0) {
      throw new UserServiceException();
    }
    boolean updated;
    try {
      updated = userDao.updateScore(score, user.getUserId());
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e);
      throw new UserServiceException(e);
    }
    if (updated) {
      user.setScore(score);
    }
    return updated;
  }

  @Override
  public boolean updateUserCash(User user, Double cash) throws UserServiceException {
    if (cash < 0) {
      throw new UserServiceException();
    }
    boolean updated;
    try {
      updated = userDao.updateCash(cash, user.getUserId());
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e);
      throw new UserServiceException(e);
    }
    if (updated) {
      user.setCash(cash);
    }
    return updated;
  }

  @Override
  public boolean updateUserImage(User user, String image) throws UserServiceException {
    boolean updated;
    try {
      updated = userDao.updateImage(image, user.getUserId());
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e);
      throw new UserServiceException(e);
    }
    if (updated) {
      user.setImage(image);
    }
    return updated;
  }

  @Override
  public boolean updateUserEnabling(User user, Boolean enabled) throws UserServiceException {
    boolean updated;
    try {
      updated = userDao.updateEnabling(enabled, user.getUserId());
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e);
      throw new UserServiceException(e);
    }
    if (updated) {
      user.setEnabled(enabled);
    }
    return updated;
  }

  @Override
  public boolean updateUserRole(User user, User.Role role) throws UserServiceException {
    boolean updated;
    try {
      updated = userDao.updateRole(role, user.getUserId());
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e);
      throw new UserServiceException(e);
    }
    if (updated) {
      user.setRole(role);
    }
    return updated;
  }

  @Override
  public boolean updateUser(User user) throws UserServiceException {
    boolean updated;
    try {
      updated = userDao.update(user);
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e);
      throw new UserServiceException(e);
    }
    return updated;
  }

  @Override
  public long calculateUsersCount() throws UserServiceException {
    long count = 0;
    try {
      count = userDao.calculateCount();
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e);
      throw new UserServiceException(e);
    }
    return count;
  }

  @Override
  public List<User> findAllUsers() throws UserServiceException {
    List<User> userList;
    try {
      userList = userDao.findAll();
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e);
      throw new UserServiceException(e);
    }
    return userList;
  }

  @Override
  public List<User> findUsersInRange(long limit, long offset) throws UserServiceException {
    List<User> userList;
    try {
      userList = userDao.findInRange(limit, offset);
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e);
      throw new UserServiceException(e);
    }
    return userList;
  }

  @Override
  public Optional<User> findUser(long userId) throws UserServiceException {
    Optional<User> optionalUser = Optional.empty();
    try {
      optionalUser = Optional.ofNullable(userDao.findEntityById(userId));
    } catch (DaoException e) {
      LOGGER.log(Level.ERROR, e);
      throw new UserServiceException(e);
    }
    return optionalUser;
  }
}

