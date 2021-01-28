package com.monkeybusiness.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataValidator {
  public static final String NICKNAME_REGEX = "[а-яА-Яa-zA-Z0-9]{2,}"; // fixme
  public static final String EMAIL_REGEX = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]+$"; // fixme
  public static final String PASSWORD_REGEX = "^[a-zA-Z0-9._-]+$"; // fixme

  private UserDataValidator() {
  }

  public static boolean isEmailValid(String email) {
    Pattern pattern = Pattern.compile(EMAIL_REGEX);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  public static boolean isNicknameValid(String nickname) {
    Pattern pattern = Pattern.compile(NICKNAME_REGEX);
    Matcher matcher = pattern.matcher(nickname);
    return matcher.matches();
  }

  public static boolean isPasswordValid(String password) {
    Pattern pattern = Pattern.compile(PASSWORD_REGEX);
    Matcher matcher = pattern.matcher(password);
    return matcher.matches();
  }

  public static boolean isLoginValid(String login) {
    boolean validNickname = isNicknameValid(login);
    boolean validEmail = isEmailValid(login);
    return validEmail || validNickname;
  }

  public static boolean isUserValid(String nickname, String email, String password) {
    boolean validNickname = isNicknameValid(nickname);
    boolean validEmail = isEmailValid(email);
    boolean validPassword = isPasswordValid(password);
    return validEmail && validPassword && validNickname;
  }
}
