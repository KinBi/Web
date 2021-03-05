package com.monkeybusiness.web.controller.command.impl;

import com.monkeybusiness.web.controller.command.Command;
import com.monkeybusiness.web.controller.RequestParameter;
import com.monkeybusiness.web.controller.SessionParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LocaleCommand implements Command {
  private static final Logger LOGGER = LogManager.getLogger();
  public static final String EN = "en";
  public static final String RU = "ru";

  @Override
  public String execute(HttpServletRequest request) {
    LOGGER.log(Level.DEBUG, "LocaleCommand has been started");
    String lang = request.getParameter(RequestParameter.LOCALE_BUTTON);
    HttpSession session = request.getSession();
    String page = (String) session.getAttribute(SessionParameter.CURRENT_PAGE_URL);
    String locale;
    switch (lang) {
      case RU:
        locale = RU;
        break;
      default:
        locale = EN;
        break;
    }
    session.setAttribute(SessionParameter.CURRENT_LOCALE, locale);
    return page;
  }
}
