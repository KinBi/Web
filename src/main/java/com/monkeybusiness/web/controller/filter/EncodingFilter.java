package com.monkeybusiness.web.controller.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter (urlPatterns = {"/*"})
public class EncodingFilter implements Filter {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final String encoding = "UTF-8";

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    LOGGER.log(Level.DEBUG, "EncodingFilter filtering");
    String codeRequest = request.getCharacterEncoding();
    if (codeRequest == null || !codeRequest.equalsIgnoreCase(encoding)) {
      request.setCharacterEncoding(encoding);
      response.setCharacterEncoding(encoding);
    }
    filterChain.doFilter(request, response);
  }
}
