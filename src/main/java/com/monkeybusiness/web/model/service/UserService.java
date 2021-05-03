package com.monkeybusiness.web.model.service;

import com.monkeybusiness.web.exception.UserServiceException;
import com.monkeybusiness.web.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
  Optional<User> register(String nickname, String email, String password) throws UserServiceException;
  Optional<User> login(String login, String password) throws UserServiceException;
  boolean updateUserNickname(User user, String nickname) throws UserServiceException;
  boolean updateUserEmail(User user, String email) throws UserServiceException;
  boolean updateUserPassword(User user, String email) throws UserServiceException;
  boolean updateUserScore(User user, Long score) throws UserServiceException;
  boolean updateUserCash(User user, Double cash) throws UserServiceException;
  boolean updateUserImage(User user, String image) throws UserServiceException;
  boolean updateUserEnabling(User user, Boolean enabled) throws UserServiceException;
  boolean updateUserRole(User user, User.Role role) throws UserServiceException;
  boolean updateUser(User user) throws UserServiceException;
  long calculateUsersCount() throws UserServiceException;
  List<User> findAllUsers() throws UserServiceException;
  List<User> findUsersInRange(long limit, long offset) throws UserServiceException;
  Optional<User> findUser(long userId) throws UserServiceException;
}

