package com.monkeybusiness.web.controller.command.impl;

import com.monkeybusiness.web.controller.RequestParameter;
import com.monkeybusiness.web.controller.UrlPath;
import com.monkeybusiness.web.controller.command.Command;
import com.monkeybusiness.web.exception.UserServiceException;
import com.monkeybusiness.web.model.entity.User;
import com.monkeybusiness.web.model.service.UserService;
import com.monkeybusiness.web.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserListChangesCommand implements Command {
  private static final Logger LOGGER = LogManager.getLogger();

  @Override
  public String execute(HttpServletRequest request) {
    LOGGER.log(Level.DEBUG, "UserListChangesCommand has been started");
    String[] idObjectValues = request.getParameterValues(RequestParameter.ID);
    if (idObjectValues != null) {
      String[] enabledObjectValues = request.getParameterValues(RequestParameter.ENABLED);
      if (enabledObjectValues != null) {
        List<String> enabledIdValues = Arrays.asList(enabledObjectValues);
        List<String> idValues = Arrays.asList(idObjectValues);
        List<String> disabledIdValues = new ArrayList<>();
        for (String id : idValues) {
          if (!enabledIdValues.contains(id)) {
            disabledIdValues.add(id);
          }
        }
        try {
          changeEnabling(enabledIdValues, true);
          changeEnabling(disabledIdValues, false);
        } catch (UserServiceException e) {
          LOGGER.log(Level.ERROR, e);
        }
      }
    }
    return UrlPath.USER_LIST_PAGE;
  }

  private void changeEnabling(List<String> userList, boolean enabling) throws UserServiceException {
    UserService userService = UserServiceImpl.INSTANCE;
    User user = new User();
    for (String id : userList) {
      user.setUserId(Long.valueOf(id));
      userService.updateUserEnabling(user, enabling);
    }
  }
}
