package com.monkeybusiness.web.controller.filter;

import com.monkeybusiness.web.controller.SessionAttribute;
import com.monkeybusiness.web.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter (urlPatterns = {"/*"})
public class GoBackActionFilter implements Filter {

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//    HttpServletRequest request = (HttpServletRequest) req;
//    HttpServletResponse response = (HttpServletResponse) resp;
//    if (request.getSession().getAttribute(SessionAttribute.USER_ROLE).equals(User.Role.GUEST)) {
//      response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
////    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
////    response.setDateHeader("Last-Modified", (new Date()).getTime()); // Proxies.
//    }
//    chain.doFilter(req, response);
//    // fixme
    HttpServletRequest request = (HttpServletRequest) req;
    HttpSession session = request.getSession();
    String page11 = request.getRequestURI();
    String page1 = request.getContextPath();
    String page2 = request.getQueryString();
    String page3 = request.getPathInfo();
    String page4 = request.getPathTranslated();
    String page5 = String.valueOf(request.getRequestURL());
    session.setAttribute(SessionAttribute.CURRENT_PAGE_URL, request.getRequestURI());
    chain.doFilter(req, resp);
  }
}
