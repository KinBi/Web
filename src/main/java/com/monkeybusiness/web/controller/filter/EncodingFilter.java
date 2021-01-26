package com.monkeybusiness.web.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName = "Encoding", urlPatterns = {"/*"},
        initParams = {@WebInitParam(name = "encoding", value = "UTF-8",
                description = "Encoding Param")})
public class EncodingFilter implements Filter {
  private String code;

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
    String codeRequest = req.getCharacterEncoding();
    if (codeRequest == null || !codeRequest.equalsIgnoreCase(code)) {
      req.setCharacterEncoding(code);
      resp.setCharacterEncoding(code);
    }
    chain.doFilter(req, resp);
  }

  public void init(FilterConfig config) throws ServletException {
    code = config.getInitParameter("encoding");
  }
}
