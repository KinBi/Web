package com.monkeybusiness.web.controller.command.impl;

import com.monkeybusiness.web.controller.UrlPath;
import com.monkeybusiness.web.controller.command.Command;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ToAdminPageCommand implements Command {
  private static final Logger LOGGER = LogManager.getLogger();

  @Override
  public String execute(HttpServletRequest request) {
    LOGGER.log(Level.DEBUG, "ToAdminPageCommand has been started");
    return UrlPath.ADMIN_PAGE;
  }
}