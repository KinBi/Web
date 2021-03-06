package com.monkeybusiness.web.controller.command.impl;

import com.monkeybusiness.web.controller.command.Command;
import com.monkeybusiness.web.controller.RequestParameter;
import com.monkeybusiness.web.exception.UserServiceException;
import com.monkeybusiness.web.model.entity.User;
import com.monkeybusiness.web.model.service.UserService;
import com.monkeybusiness.web.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UserCommand implements Command {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final UserService service = UserServiceImpl.INSTANCE;

  @Override
  public String execute(HttpServletRequest request) {
    // todo
    LOGGER.log(Level.DEBUG, "UserCommand has been started");
    HttpSession session = request.getSession();
    String page = request.getParameter(RequestParameter.CURRENT_PAGE);
    List<User> userList;
    try {
      userList = service.findAllUsers();
      request.setAttribute(RequestParameter.USER_LIST, userList);
    } catch (UserServiceException e) {
      LOGGER.log(Level.ERROR, e);
    }
    return page;
  }
}
