package com.monkeybusiness.web.model.dao;

import com.monkeybusiness.web.exception.DaoException;
import com.monkeybusiness.web.model.entity.User;

import java.util.List;

public interface UserDao extends Dao<User> {
  List<User> findInRange(long limit, long offset) throws DaoException;
  boolean login(User user, String password) throws DaoException;
  boolean updateNickname(String nickname, long userId) throws DaoException;
  boolean updateEmail(String email, long userId) throws DaoException;
  boolean updatePassword(String password, long userId) throws DaoException;
  boolean updateScore(long score, long userId) throws DaoException;
  boolean updateCash(double cash, long userId) throws DaoException;
  boolean updateImage(String image, long userId) throws DaoException;
  boolean updateEnabling(boolean enabled, long userId) throws DaoException;
  boolean updateRole(User.Role role, long userId) throws DaoException;
}
