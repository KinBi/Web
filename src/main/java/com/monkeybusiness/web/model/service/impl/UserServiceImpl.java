package com.monkeybusiness.web.model.service.impl;

import com.monkeybusiness.web.exception.DaoException;
import com.monkeybusiness.web.exception.UserServiceException;
import com.monkeybusiness.web.model.dao.EntityTransaction;
import com.monkeybusiness.web.model.dao.UserDao;
import com.monkeybusiness.web.model.entity.User;
import com.monkeybusiness.web.model.service.UserService;
import com.monkeybusiness.web.validator.UserDataValidator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserServiceImpl implements UserService {
  private static final Logger logger = LogManager.getLogger();
  private static final UserDao dao = new UserDao();

  @Override
  public Optional<User> registrate(String nickname, String email, String password) throws UserServiceException {
    if (!UserDataValidator.isUserValid(nickname, email, password)) {
      throw new UserServiceException();
    }
    Optional<User> registratedUser = Optional.empty();
    EntityTransaction transaction = new EntityTransaction();
    transaction.begin(dao);
    User user = new User(nickname, email);
    try {
      if (dao.create(user, password)) {
        registratedUser = Optional.of(user);
      }
      transaction.commit();
    } catch (DaoException e) {
      e.printStackTrace();
    } finally {
      transaction.end();
    }

    return registratedUser;
  }

  @Override
  public Optional<User> login(String login, String password) throws UserServiceException {
    if (!UserDataValidator.isLoginValid(login)) {
      throw new UserServiceException();
    }
    Optional<User> loginedUser = Optional.empty();
    EntityTransaction transaction = new EntityTransaction();
    transaction.begin(dao);
    User user = new User(login, login);
    try {
      if (dao.login(user, password)) {
        loginedUser = Optional.of(user);
      }
      transaction.commit();
    } catch (DaoException e) {
      e.printStackTrace();
    } finally {
      transaction.end();
    }
    return loginedUser;
  }
}

