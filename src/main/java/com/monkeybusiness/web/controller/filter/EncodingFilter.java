package com.monkeybusiness.web.controller.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter
public class EncodingFilter implements Filter {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final String encoding = "UTF-8";

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
    LOGGER.log(Level.DEBUG, "EncodingFilter filtering");
    String codeRequest = req.getCharacterEncoding();
    if (codeRequest == null || !codeRequest.equalsIgnoreCase(encoding)) {
      req.setCharacterEncoding(encoding);
      resp.setCharacterEncoding(encoding);
    }
    chain.doFilter(req, resp);
  }
}
