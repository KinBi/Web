package com.monkeybusiness.web.controller.command.impl;

import com.monkeybusiness.web.controller.command.Command;
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
import java.util.ArrayList;
import java.util.List;

public class UserCommand implements Command {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final String ADMIN = "admin";
  private static final String USER = "user";
  private static final UserServiceImpl service = new UserServiceImpl();

  @Override
  public String execute(HttpServletRequest request) {
    // todo
    LOGGER.log(Level.DEBUG, "UserCommand has been started");
    HttpSession session = request.getSession();
    String page = request.getParameter(RequestParameter.CURRENT_PAGE);
    List<User> userList = new ArrayList<>();
    try {
      userList = service.findAllUsers();
      request.setAttribute(RequestParameter.USER_LIST, userList);
    } catch (UserServiceException e) {
      LOGGER.log(Level.ERROR, e);
    }
    page = page.substring(request.getContextPath().length());
    return page;
  }
}
