package com.monkeybusiness.web.controller.command.impl;

import com.monkeybusiness.web.controller.SessionAttribute;
import com.monkeybusiness.web.controller.UrlPath;
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

public class UserListCommand implements Command {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final UserService service = UserServiceImpl.INSTANCE;

  @Override
  public String execute(HttpServletRequest request) {
    // todo
    LOGGER.log(Level.DEBUG, "UserCommand has been started");
    HttpSession session = request.getSession();
    List<User> userList;
    try {
      userList = service.findAllUsers();
      request.setAttribute(RequestParameter.USER_LIST, userList);
    } catch (UserServiceException e) {
      LOGGER.log(Level.ERROR, e);
      request.setAttribute(RequestParameter.GET_USER_LIST_RESULT, "Getting users operation has been failed");
    }
   return UrlPath.USER_LIST_PAGE;
  }
}
