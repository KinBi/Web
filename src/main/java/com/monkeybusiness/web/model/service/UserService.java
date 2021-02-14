package com.monkeybusiness.web.model.service;

import com.monkeybusiness.web.exception.UserServiceException;
import com.monkeybusiness.web.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
  Optional<User> registrate(String nickname, String email, String password) throws UserServiceException;
  Optional<User> login(String login, String password) throws UserServiceException;
  List<User> findAllUsers() throws UserServiceException;
}

