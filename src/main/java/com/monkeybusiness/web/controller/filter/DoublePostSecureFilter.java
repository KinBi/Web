package com.monkeybusiness.web.controller.filter;

import com.monkeybusiness.web.controller.RequestParameter;
import com.monkeybusiness.web.controller.SessionAttribute;

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

  // fixme
  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
    request = (HttpServletRequest) req;
    if (request.getMethod().equals(GET_METHOD)) {
      session = request.getSession(true);
      session.setAttribute(SessionAttribute.SERVER_TOKEN, new Random().nextInt(TOKEN_MAX_VALUE));
      chain.doFilter(req, resp);
    } else {
      serverToken = (Integer) session.getAttribute(SessionAttribute.SERVER_TOKEN);
      System.out.println("serverToken:" + serverToken);
      clientToken = Integer.parseInt(req.getParameter(RequestParameter.CLIENT_TOKEN));
      System.out.println("clientToken:" + clientToken);
      System.out.println(clientToken + "----------" + serverToken);
      if (serverToken == clientToken) {
        session.setAttribute(SessionAttribute.SERVER_TOKEN, new Random().nextInt(TOKEN_MAX_VALUE));
        chain.doFilter(req, resp);
      } else {
        String page = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE_URL);
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        dispatcher.forward(req, resp);
      }
    }
  }
}
