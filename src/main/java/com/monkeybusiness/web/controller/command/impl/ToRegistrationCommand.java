package com.monkeybusiness.web.controller.command.impl;

import com.monkeybusiness.web.controller.command.Command;
import com.monkeybusiness.web.controller.command.JspPath;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ToRegistrationCommand implements Command {
  private static final Logger LOGGER = LogManager.getLogger();

  @Override
  public String execute(HttpServletRequest request) {
    LOGGER.log(Level.DEBUG, "ToRegistrationCommand has been started");
    return JspPath.TO_REGISTRATION;
  }
}
