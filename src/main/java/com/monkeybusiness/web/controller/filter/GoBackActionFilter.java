package com.monkeybusiness.web.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class GoBackActionFilter implements Filter {
  public static final String GET = "GET";

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
    HttpServletResponse response = (HttpServletResponse) resp;
    // fixme
//      response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
      response.setHeader("Cache-Control", "no-cache, no-store"); // HTTP 1.1.
//    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//    response.setDateHeader("Last-Modified", (new Date()).getTime()); // Proxies.
    chain.doFilter(req, response);
  }
}
