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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public enum UserServiceImpl implements UserService {
  INSTANCE;

  private static final Logger logger = LogManager.getLogger();
  private final UserDao userDao = UserDaoImpl.INSTANCE;

  @Override
  public Optional<User> registrate(String nickname, String email, String password) throws UserServiceException {
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
      logger.log(Level.ERROR, e);
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
      logger.log(Level.ERROR, e);
      throw new UserServiceException(e);
    }
    return loginedUser;
  }

  @Override
  public List<User> findAllUsers() throws UserServiceException {
    List<User> userList = new ArrayList<>();
    try {
      userList = userDao.findAll();
    } catch (DaoException e) {
      logger.log(Level.ERROR, e);
      throw new UserServiceException(e);
    }
    return userList;
  }
}

