package com.monkeybusiness.web.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
  String execute(HttpServletRequest request);
}