package com.monkeybusiness.web.controller.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class CommandProvider {
  private static final Logger LOGGER = LogManager.getLogger();

  private CommandProvider() {
  }

  public static Optional<Command> defineCommand(String commandName) {
    if (commandName == null || commandName.isBlank()) {
      return Optional.empty();
    }
    Optional<Command> current;
    try {
      CommandType type = CommandType.valueOf(commandName.toUpperCase());
      current = Optional.of(type.getCurrentCommand());
    } catch (IllegalArgumentException e) {
      LOGGER.log(Level.ERROR, e);
      current = Optional.empty();
    }
    return current;
  }
}