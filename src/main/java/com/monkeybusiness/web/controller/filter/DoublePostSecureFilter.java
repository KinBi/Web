package com.monkeybusiness.web.controller.filter;

import com.monkeybusiness.web.controller.RequestParameter;
import com.monkeybusiness.web.controller.SessionParameter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@WebFilter
public class DoublePostSecureFilter implements Filter {
  private static final String ERROR_PAGE = "404";
  private static final String GET_METHOD = "GET";
  private static final int TOKEN_MAX_VALUE = 10000;
  private HttpSession session;
  private HttpServletRequest request;
  private int serverToken = 0;
  private int clientToken = 0;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // init
  }

  // fixme
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    request = (HttpServletRequest) servletRequest;
    if (request.getMethod().equals(GET_METHOD)) {
      session = request.getSession(true);
      session.setAttribute(SessionParameter.SERVER_TOKEN, new Random().nextInt(TOKEN_MAX_VALUE));
      filterChain.doFilter(servletRequest, servletResponse);
    } else {
      serverToken = (Integer) session.getAttribute(SessionParameter.SERVER_TOKEN);
      System.out.println("serverToken:" + serverToken);
      clientToken = Integer.parseInt(servletRequest.getParameter(RequestParameter.CLIENT_TOKEN));
      System.out.println("clientToken:" + clientToken);
      System.out.println(clientToken + "----------" + serverToken);
      if (serverToken == clientToken) {
        session.setAttribute(SessionParameter.SERVER_TOKEN, new Random().nextInt(TOKEN_MAX_VALUE));
        filterChain.doFilter(servletRequest, servletResponse);
      } else {
        String page = (String) session.getAttribute(SessionParameter.CURRENT_PAGE_URL);
        RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(page);
        dispatcher.forward(servletRequest, servletResponse);
      }
    }
  }

  @Override
  public void destroy() {
    // destroy
  }
}
