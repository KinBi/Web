package com.monkeybusiness.web.controller.command.impl;

import com.monkeybusiness.web.controller.UrlPath;
import com.monkeybusiness.web.controller.command.Command;
import com.monkeybusiness.web.controller.RequestParameter;
import com.monkeybusiness.web.controller.SessionParameter;
import com.monkeybusiness.web.exception.UserServiceException;
import com.monkeybusiness.web.model.entity.User;
import com.monkeybusiness.web.model.service.UserService;
import com.monkeybusiness.web.model.service.impl.UserServiceImpl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final String ERROR_MESSAGE = "Invalid datata, I'm blue da ba dee da ba die";
  private static final UserService service = UserServiceImpl.INSTANCE;

  @Override
  public String execute(HttpServletRequest request) {
    LOGGER.log(Level.DEBUG, "LoginCommand has been started");
    String page;
    // fixme
    HttpSession session = request.getSession();
    String login = request.getParameter(RequestParameter.LOGIN);
    String password = request.getParameter(RequestParameter.PASSWORD);
    try {
      Optional<User> loginResult = service.login(login, password);
      if (loginResult.isPresent()) {
        User user = loginResult.get();
        session.setAttribute(SessionParameter.USER_ROLE, user.getRole());
        session.setAttribute(SessionParameter.USER_NICKNAME, user.getNickname());
        session.setAttribute(SessionParameter.USER_EMAIL, user.getEmail());
        session.setAttribute(SessionParameter.USER_CASH, user.getCash());
        session.setAttribute(SessionParameter.USER_SCORE, user.getScore());
        page = UrlPath.MAIN;
        LOGGER.log(Level.INFO, "Login has been finished successful");
      } else {
        LOGGER.log(Level.WARN, "Login failed: Caused by Reptilian");
        request.setAttribute(RequestParameter.LOGIN_PASS_ERROR_MESSAGE, ERROR_MESSAGE);
        page = UrlPath.LOGIN;
      }
    } catch (UserServiceException e) {
      LOGGER.log(Level.ERROR, e);
      request.setAttribute(RequestParameter.LOGIN_PASS_ERROR_MESSAGE, ERROR_MESSAGE);
      page = UrlPath.LOGIN;
    }
    return page;
  }
}
