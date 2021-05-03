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

@WebFilter
public class GuestFilter implements Filter {
  private static final Logger LOGGER = LogManager.getLogger();

  @Override
  public void init(FilterConfig config) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
    LOGGER.log(Level.DEBUG, "GuestFilter has been filtering");
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;
    HttpSession session = request.getSession();
    User.Role role = (User.Role) session.getAttribute(SessionAttribute.USER_ROLE);
    if (role == null || !role.equals(User.Role.GUEST)) {
      String page = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE_URL);
      response.sendRedirect(request.getContextPath() + page);
    }
    chain.doFilter(req, resp);
  }

  @Override
  public void destroy() {
  }

}
