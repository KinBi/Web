package com.monkeybusiness.web.controller.command.impl;

import com.monkeybusiness.web.controller.command.Command;
import com.monkeybusiness.web.controller.command.JspPath;
import com.monkeybusiness.web.controller.command.RequestParameter;
import com.monkeybusiness.web.exception.UserServiceException;
import com.monkeybusiness.web.model.entity.User;
import com.monkeybusiness.web.model.service.impl.UserServiceImpl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements Command {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final String ERROR_MESSAGE = "Invalid data";

  private static final UserServiceImpl service = new UserServiceImpl();

  @Override
  public String execute(HttpServletRequest request) {
    LOGGER.log(Level.DEBUG, "LoginCommand has been started");
    String page;
    String button = request.getParameter(RequestParameter.LOGIN_BUTTON_NAME);
    if (button == null) {
      page = JspPath.TO_REGISTRATION;
    } else {
      String login = request.getParameter(RequestParameter.LOGIN);
      String password = request.getParameter(RequestParameter.PASSWORD);
      try {
        Optional<User> loginResult = service.login(login, password);
        if (loginResult.isPresent()) {
          User user = loginResult.get();
          request.setAttribute(RequestParameter.USER, user.getNickname());
          page = JspPath.TO_MAIN;
          LOGGER.log(Level.INFO, "Login has been finished successful");
        } else {
          LOGGER.log(Level.WARN, "Login failed: Caused by Reptilian");
          request.setAttribute(RequestParameter.REGISTRATION_ERROR_MESSAGE, ERROR_MESSAGE);
          page = JspPath.TO_LOGIN;
        }
      } catch (UserServiceException e) {
        LOGGER.log(Level.ERROR, e);
        request.setAttribute(RequestParameter.REGISTRATION_ERROR_MESSAGE, ERROR_MESSAGE);
        page = JspPath.TO_LOGIN;
      }
    }
    return page;
  }
}
