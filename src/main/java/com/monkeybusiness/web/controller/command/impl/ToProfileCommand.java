package com.monkeybusiness.web.controller.command.impl;

import com.monkeybusiness.web.controller.JspPath;
import com.monkeybusiness.web.controller.SessionParameter;
import com.monkeybusiness.web.controller.UrlPath;
import com.monkeybusiness.web.controller.command.Command;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ToProfileCommand implements Command {
  private static final Logger LOGGER = LogManager.getLogger();

  @Override
  public String execute(HttpServletRequest request) {
    LOGGER.log(Level.DEBUG, "ToProfileCommand has been started");
    return UrlPath.PROFILE;
  }
}
