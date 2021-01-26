package com.monkeybusiness.web.controller;

import com.monkeybusiness.web.controller.command.Command;
import com.monkeybusiness.web.controller.command.CommandProvider;
import com.monkeybusiness.web.controller.command.JspPath;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {
  private static final Logger LOGGER = LogManager.getLogger();
  public static final String COMMAND = "command";

  public void init() {
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    processRequest(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    processRequest(request, response);
  }

  private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    Optional<Command> optionalCommand = CommandProvider.defineCommand(request.getParameter(COMMAND));
    Command command = optionalCommand.orElseThrow(IllegalArgumentException::new);
    String page = command.execute(request);
    LOGGER.info("|||||||||||||||||||||Servlet started...|||||||||||||||||||||");
    if (page != null) {
      RequestDispatcher dispatcher = request.getRequestDispatcher(page);
      dispatcher.forward(request, response);
    } else {
      LOGGER.log(Level.ERROR, "Page is not found");
      page = JspPath.TO_ERROR404;
      response.sendRedirect(request.getContextPath() + page);
    }
  }

  public void destroy() {
  }
}