package com.monkeybusiness.web.controller.filter;

import com.monkeybusiness.web.controller.JspPath;
import com.monkeybusiness.web.controller.SessionAttribute;
import com.monkeybusiness.web.controller.UrlPath;
import com.monkeybusiness.web.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class AbstractSecurityFilter implements Filter {

  public static final int ERROR_404 = 404;

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
  }

  public void forward(HttpServletRequest request, HttpServletResponse response,
                      boolean hasAccess, String uri) throws ServletException, IOException {
    if (hasAccess) {
      String page = uri.substring(request.getContextPath().length());
      request.getSession().setAttribute(SessionAttribute.CURRENT_PAGE_URL, page);
      request.getRequestDispatcher(page).forward(request, response);
    } else {
      response.sendError(ERROR_404);
    }
  }

  public void redirect(HttpServletRequest request, HttpServletResponse response, String redirectPage) throws IOException {
    if (redirectPage == null || redirectPage.isBlank()) {
      response.sendError(ERROR_404);
    } else {
      request.getSession().setAttribute(SessionAttribute.CURRENT_PAGE_URL, redirectPage);
      response.sendRedirect(request.getContextPath() + redirectPage);
    }
  }

  public boolean hasAccess(User.Role userRole, User.Role... roles) {
    boolean result = false;
    for (User.Role role : roles) {
      if (userRole == role) {
        result = true;
        break;
      }
    }
    return result;
  }

}
