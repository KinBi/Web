package com.monkeybusiness.web.controller.filter;

import com.monkeybusiness.web.controller.SessionParameter;
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
  public void init(FilterConfig filterConfig) throws ServletException {
    // todo init
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    LOGGER.log(Level.DEBUG, "AdminFilter filtering");
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response  = (HttpServletResponse) servletResponse;
    HttpSession session = request.getSession();
    User.Role role = (User.Role) session.getAttribute(SessionParameter.USER_ROLE);
    if (role == null || !role.equals(User.Role.ADMIN)) {
      String page = (String) session.getAttribute(SessionParameter.CURRENT_PAGE_URL);
      LOGGER.log(Level.DEBUG, "User not admin " + page);
      response.sendError(ERROR_404);
    }
    filterChain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    // todo destroy
  }
}
