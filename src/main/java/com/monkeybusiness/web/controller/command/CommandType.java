package com.monkeybusiness.web.controller.command;

import com.monkeybusiness.web.controller.command.impl.*;

public enum CommandType {
  LOGIN (new LoginCommand()),
  LOGOUT (new LogoutCommand()),
  REGISTRATION (new RegistrationCommand()),
  ADV (new AdvertisingCommand()),
  LOCALE (new LocaleCommand()),
  USER_LIST(new UserListCommand()),
  USER_LIST_CHANGES(new UserListChangesCommand()),
  GET_CARD(new GetCardCommand()),
  PASS_THE_MOVE(new PassTheMoveCommand()),
  END_GAME(new EndGameCommand());

  private final Command command;

  CommandType(Command command) {
    this.command = command;
  }

  public Command getCurrentCommand() {
    return command;
  }
}
