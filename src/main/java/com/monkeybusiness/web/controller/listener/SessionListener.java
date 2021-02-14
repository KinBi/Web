package com.monkeybusiness.web.controller.listener;

import com.monkeybusiness.web.controller.command.JspPath;
import com.monkeybusiness.web.controller.command.SessionParameter;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
  @Override
  public void sessionCreated(HttpSessionEvent sessionEvent) {
    // fixme
    HttpSession session = sessionEvent.getSession();
//    session.setAttribute(SessionAttribute.USER_ROLE, SessionAttribute.GUEST_ROLE);
    session.setAttribute(SessionParameter.CURRENT_LOCALE, "ru");
    session.setAttribute(SessionParameter.CURRENT_PAGE, JspPath.TO_REGISTRATION);
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent sessionEvent) {
  }
}
