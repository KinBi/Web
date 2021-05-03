package com.monkeybusiness.web.controller.servlet;

import com.monkeybusiness.web.controller.RequestParameter;
import com.monkeybusiness.web.controller.SessionAttribute;
import com.monkeybusiness.web.controller.UrlPath;
import com.monkeybusiness.web.controller.command.Command;
import com.monkeybusiness.web.controller.command.CommandProvider;
import com.monkeybusiness.web.exception.UserServiceException;
import com.monkeybusiness.web.model.entity.User;
import com.monkeybusiness.web.model.service.UserService;
import com.monkeybusiness.web.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = {"/admin/userList.do"})
public class UserListServlet extends HttpServlet {
  private static final Logger LOGGER = LogManager.getLogger();
  private static final String COMMAND = "command";
  private static final int FIRST_PAGE = 1;
  private static final int RECORDS_PER_PAGE = 8;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    LOGGER.info("|||||||||||||||||||||UserList get request processed...|||||||||||||||||||||");
    int pageNum = FIRST_PAGE;
    String page = request.getParameter(RequestParameter.PAGE);
    if (page != null) {
      pageNum = Integer.parseInt(page);
    }
    UserService service = UserServiceImpl.INSTANCE;
    try {
      List<User> userList = service.findUsersInRange(RECORDS_PER_PAGE, (pageNum - 1) * RECORDS_PER_PAGE);
      long countOfRecords = service.calculateUsersCount();
      long countOfPages = (long) Math.ceil(countOfRecords * 1. / RECORDS_PER_PAGE);
      request.setAttribute(RequestParameter.USER_LIST, userList);
      request.setAttribute(RequestParameter.COUNT_OF_PAGES, countOfPages);
      request.setAttribute(RequestParameter.CURRENT_PAGE, page);
    } catch (UserServiceException e) {
      LOGGER.log(Level.ERROR, e); // todo
    }
    RequestDispatcher dispatcher = request.getRequestDispatcher(UrlPath.USER_LIST_PAGE);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Optional<Command> optionalCommand = CommandProvider.defineCommand(request.getParameter(COMMAND));
    Command command = optionalCommand.orElseThrow(IllegalArgumentException::new);
    command.execute(request);
    LOGGER.info("|||||||||||||||||||||UserList post request processed...|||||||||||||||||||||");
    doGet(request, response);
  }
}
