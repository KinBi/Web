package com.monkeybusiness.web.controller.listener;

import com.monkeybusiness.web.controller.SessionParameter;
import com.monkeybusiness.web.controller.UrlPath;
import com.monkeybusiness.web.model.entity.User;

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
    session.setAttribute(SessionParameter.USER_ROLE, User.Role.GUEST);
    session.setAttribute(SessionParameter.CURRENT_LOCALE, "ru");
    session.setAttribute(SessionParameter.CURRENT_PAGE_URL, "/registration");
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent sessionEvent) {
  }
}
