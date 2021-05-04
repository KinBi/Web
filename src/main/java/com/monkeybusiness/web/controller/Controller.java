package com.monkeybusiness.web.controller;

import com.monkeybusiness.web.controller.command.Command;
import com.monkeybusiness.web.controller.command.CommandProvider;

import com.monkeybusiness.web.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final String COMMAND = "command";
  private static final int ERROR_CODE = 404;

  @Override
  public void init() {
    // init smth
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    processRequest(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    processRequest(request, response);
  }

  private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    Optional<Command> optionalCommand = CommandProvider.defineCommand(request.getParameter(COMMAND));
    Command command = optionalCommand.orElseThrow(IllegalArgumentException::new);
    String page = command.execute(request);
    LOGGER.info("|||||||||||||||||||||Request processed...|||||||||||||||||||||");
    String page11 = request.getRequestURI();
    String page1 = request.getContextPath();
    String page2 = request.getQueryString();
    String page3 = request.getPathInfo();
    String page4 = request.getPathTranslated();
    String page5 = String.valueOf(request.getRequestURL());
    if (page != null) {
      if (request.getSession().getAttribute(SessionAttribute.CURRENT_PAGE_URL).equals(page)) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
      } else {
        response.sendRedirect(request.getContextPath() + page);
//        request.getSession().setAttribute(SessionAttribute.CURRENT_PAGE_URL, page);
      }
    } else {
      LOGGER.log(Level.ERROR, "Page is not found");
      response.sendError(ERROR_CODE);
    }
  }

  @Override
  public void destroy() {
    ConnectionPool.INSTANCE.destroyPool();
    DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
      try {
        DriverManager.deregisterDriver(driver);
      } catch (SQLException e) {
        LOGGER.log(Level.ERROR, e);
      }
    });
  }
}