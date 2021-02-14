package com.monkeybusiness.web.controller.command.impl;

import com.monkeybusiness.web.controller.command.Command;
import com.monkeybusiness.web.controller.command.JspPath;
import com.monkeybusiness.web.controller.command.RequestParameter;
import com.monkeybusiness.web.controller.command.SessionParameter;
import com.monkeybusiness.web.exception.UserServiceException;
import com.monkeybusiness.web.model.entity.User;
import com.monkeybusiness.web.model.service.impl.UserServiceImpl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class RegistrationCommand implements Command {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final String ERROR_MESSAGE = "Invalid data";

  private static final UserServiceImpl service = new UserServiceImpl();

  @Override
  public String execute(HttpServletRequest request) {
    LOGGER.log(Level.DEBUG, "RegistrationCommand has been started");
    String page;
    // fixme
    HttpSession session = request.getSession();
    String email = request.getParameter(RequestParameter.EMAIL);
    String nickname = request.getParameter(RequestParameter.NICKNAME);
    String password = request.getParameter(RequestParameter.PASSWORD);
    try {
      Optional<User> optionalUser = service.registrate(nickname, email, password);
      if (optionalUser.isPresent()) {
        request.setAttribute(RequestParameter.USER, nickname);
        // fixme
        User user = optionalUser.get();
        session.setAttribute(SessionParameter.USER, user);
        page = JspPath.TO_MAIN;
      } else {
        LOGGER.log(Level.WARN, "Registration failed: Caused by Reptilian");
        request.setAttribute(RequestParameter.REGISTRATION_ERROR_MESSAGE, ERROR_MESSAGE);
        page = JspPath.TO_REGISTRATION;
      }
    } catch (UserServiceException e) {
      LOGGER.log(Level.ERROR, e);
      request.setAttribute(RequestParameter.REGISTRATION_ERROR_MESSAGE, ERROR_MESSAGE);
      page = JspPath.TO_REGISTRATION;
    }

    return page;
  }
}
