package com.monkeybusiness.web.controller.listener;

import com.monkeybusiness.web.controller.SessionAttribute;
import com.monkeybusiness.web.controller.UrlPath;
import com.monkeybusiness.web.model.entity.User;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;

@WebListener
public class SessionListener implements HttpSessionListener {
  @Override
  public void sessionCreated(HttpSessionEvent sessionEvent) {
    // fixme
    HttpSession session = sessionEvent.getSession();
    session.setAttribute(SessionAttribute.USER_ROLE, User.Role.GUEST);
    session.setAttribute(SessionAttribute.CURRENT_LOCALE, Locale.ENGLISH.getLanguage());
    session.setAttribute(SessionAttribute.CURRENT_PAGE_URL, UrlPath.REGISTRATION);
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent sessionEvent) {
  }
}
