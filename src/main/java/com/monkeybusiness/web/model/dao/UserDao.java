package com.monkeybusiness.web.model.dao;

import com.monkeybusiness.web.exception.DaoException;
import com.monkeybusiness.web.model.entity.User;

public interface UserDao extends Dao<User> {
  boolean login(User entity, String password) throws DaoException;
}
