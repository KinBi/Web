package com.monkeybusiness.web.controller.filter;

import com.monkeybusiness.web.controller.SessionAttribute;
import com.monkeybusiness.web.controller.UrlPath;
import com.monkeybusiness.web.model.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter
public class UserFilter extends AbstractSecurityFilter implements Filter {
  private static final Logger LOGGER = LogManager.getLogger();

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
    LOGGER.log(Level.DEBUG, "UserFilter has been filtering");
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;
    HttpSession session = request.getSession();
    String uri = request.getRequestURI();
    boolean hasAccess = hasAccess((User.Role) session.getAttribute(SessionAttribute.USER_ROLE), User.Role.USER, User.Role.ADMIN);
    if (!hasAccess) {
      LOGGER.log(Level.INFO, "User has not access to page");
      String redirectPage = UrlPath.LOGIN;
      redirect(request, response, redirectPage);
    } else {
      forward(request, response, hasAccess, uri);
    }
  }
}
