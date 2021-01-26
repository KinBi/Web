package com.monkeybusiness.web.controller.command.impl;

import com.monkeybusiness.web.controller.command.Command;
import com.monkeybusiness.web.controller.command.JspPath;
import com.monkeybusiness.web.controller.command.RequestParameter;
import com.monkeybusiness.web.exception.UserServiceException;
import com.monkeybusiness.web.model.service.impl.UserServiceImpl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class RegistrationCommand implements Command {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final String ERROR_MESSAGE = "Invalid data";
  private static final String RU = "ru";
  private static final String EN = "en";

  private final UserServiceImpl service;

  public RegistrationCommand(UserServiceImpl service) {
    this.service = service;
  }

  @Override
  public String execute(HttpServletRequest request) {
    LOGGER.log(Level.DEBUG, "RegistrationCommand has been started");
    String page;
    String button = request.getParameter(RequestParameter.REGISTER_BUTTON);
    if (button == null) {
      Locale locale = request.getLocale();
      if (locale.getLanguage().equals(RU)) {
        Locale.setDefault(new Locale(EN));
      } else {
        Locale.setDefault(new Locale(RU));
      }
      page = JspPath.TO_LOGIN;
    } else {
      String email = request.getParameter(RequestParameter.EMAIL);
      String nickname = request.getParameter(RequestParameter.NICKNAME);
      String password = request.getParameter(RequestParameter.PASSWORD);
      try {
        if (service.registrate(nickname, email, password).isPresent()) {
          request.setAttribute(RequestParameter.USER, nickname);
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
    }
    return page;
  }
}
