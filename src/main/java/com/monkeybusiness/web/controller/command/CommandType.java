package com.monkeybusiness.web.controller.command;

import com.monkeybusiness.web.controller.command.impl.*;

public enum CommandType {
  LOGIN (new LoginCommand()),
  LOGOUT (new LogoutCommand()),
  REGISTRATION (new RegistrationCommand()),
  ADV (new AdvertisingCommand()),
  LOCALE (new LocaleCommand()),
  USER (new UserCommand()),
  TO_LOGIN (new ToLoginCommand()),
  TO_REGISTRATION (new ToRegistrationCommand()),
  TO_ADMIN_PAGE (new ToAdminPageCommand()),
  TO_PROFILE (new ToProfileCommand()),
  TO_USER_LIST (new ToUserListCommand());

  private final Command command;

  CommandType(Command command) {
    this.command = command;
  }

  public Command getCurrentCommand() {
    return command;
  }
}
