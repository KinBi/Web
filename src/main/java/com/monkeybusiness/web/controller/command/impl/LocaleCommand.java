package com.monkeybusiness.web.controller.command.impl;

import com.monkeybusiness.web.controller.command.Command;
import com.monkeybusiness.web.controller.command.RequestParameter;
import com.monkeybusiness.web.controller.command.SessionParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LocaleCommand implements Command {
  public static final String EN = "en";
  public static final String RU = "ru";

  @Override
  public String execute(HttpServletRequest request) {
    String page = request.getParameter(RequestParameter.CURRENT_PAGE);
    String lang = request.getParameter(RequestParameter.LOCALE_BUTTON);
    HttpSession session = request.getSession();
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
    page = page.substring(request.getContextPath().length());
    return page;
  }
}
