package com.monkeybusiness.web.controller.filter;

import com.monkeybusiness.web.controller.SessionAttribute;
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

// todo
@WebFilter
public class AdminFilter implements Filter {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final int ERROR_404 = 404;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    LOGGER.log(Level.DEBUG, "AdminFilter filtering");
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response  = (HttpServletResponse) servletResponse;
    HttpSession session = request.getSession();
    User.Role role = (User.Role) session.getAttribute(SessionAttribute.USER_ROLE);
    if (role == null || !role.equals(User.Role.ADMIN)) {
      LOGGER.log(Level.DEBUG, "User not admin");
      response.sendError(ERROR_404);
    }
    filterChain.doFilter(request, response);
  }
}
