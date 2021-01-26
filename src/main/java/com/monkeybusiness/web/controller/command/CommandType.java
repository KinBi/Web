package com.monkeybusiness.web.controller.command;

import com.monkeybusiness.web.controller.command.impl.*;
import com.monkeybusiness.web.model.service.impl.UserServiceImpl;

public enum CommandType {
  LOGIN (new LoginCommand(new UserServiceImpl())),
  LOGOUT (new LogoutCommand()),
  REGISTRATION (new RegistrationCommand(new UserServiceImpl())),
  ADV (new AdvCommand());

  private final Command command;

  CommandType(Command command) {
    this.command = command;
  }

  public Command getCurrentCommand() {
    return command;
  }
}
