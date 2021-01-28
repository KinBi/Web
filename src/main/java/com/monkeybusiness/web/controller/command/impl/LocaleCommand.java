package com.monkeybusiness.web.controller.command.impl;

import com.monkeybusiness.web.controller.command.Command;
import com.monkeybusiness.web.controller.command.JspPath;
import com.monkeybusiness.web.controller.command.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LocaleCommand implements Command {

  @Override
  public String execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.setAttribute(RequestParameter.CURRENT_LOCALE, "ru");
//    return session.getServletContext().getContextPath(); // fixme
    return JspPath.TO_REGISTRATION; // fixme
  }
}
