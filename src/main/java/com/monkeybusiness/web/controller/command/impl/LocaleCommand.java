package com.monkeybusiness.web.controller.command.impl;

import com.monkeybusiness.web.controller.command.Command;
import com.monkeybusiness.web.controller.command.JspPath;
import com.monkeybusiness.web.controller.command.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class LocaleCommand implements Command {
  public static final String EN = "en";
  public static final String RU = "ru";

  @Override
  public String execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    Locale locale = (Locale) session.getAttribute(RequestParameter.CURRENT_LOCALE);
    if (locale == null || locale.getLanguage().equals(EN)) {
      locale = new Locale(RU);
    } else {
      locale = new Locale(EN);
    }
    session.setAttribute(RequestParameter.CURRENT_LOCALE, locale);
//    return session.getServletContext().getContextPath(); // fixme
    return request.getHeader("referer"); // fixme
  }
}
