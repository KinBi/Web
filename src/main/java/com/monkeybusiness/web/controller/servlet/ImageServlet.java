package com.monkeybusiness.web.controller.servlet;

import com.monkeybusiness.web.controller.SessionAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(urlPatterns = {"/images/*"})
public class ImageServlet extends HttpServlet {
  private static final Logger LOGGER = LogManager.getLogger();

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    LOGGER.log(Level.DEBUG, "ImageServlet has been processed request");
    HttpSession session = request.getSession();
    String image = (String) session.getAttribute(SessionAttribute.USER_IMAGE);
    byte[] imageBytes = new FileInputStream(image).readAllBytes();
    response.setContentType("image/jpg");
    response.setContentLength(imageBytes.length);
    response.getOutputStream().write(imageBytes);
  }
}
