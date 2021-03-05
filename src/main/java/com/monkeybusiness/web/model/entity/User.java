package com.monkeybusiness.web.model.entity;

public class User implements Entity {
  public enum Role {
    ADMIN,
    USER,
    GUEST
  }

  private long userId;
  private String nickname;
  private String email;
  private long score;
  private double cash;
  private Role role;

  public User() {
    this.role = Role.USER;
  }

  public User(String nickname, String email) {
    this.nickname = nickname;
    this.email = email;
    this.role = Role.USER;
  }

  public User(String nickname, String email, long score, double cash) {
    this.nickname = nickname;
    this.email = email;
    this.score = score;
    this.cash = cash;
    this.role = Role.USER;
  }

  public User(String nickname, String email, long score, double cash, Role role) {
    this.nickname = nickname;
    this.email = email;
    this.score = score;
    this.cash = cash;
    this.role = role;
  }

  public User(long userId, String nickname, String email, long score, double cash, Role role) {
    this.userId = userId;
    this.nickname = nickname;
    this.email = email;
    this.score = score;
    this.cash = cash;
    this.role = role;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }

  public double getCash() {
    return cash;
  }

  public void setCash(double cash) {
    this.cash = cash;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (userId != user.userId) return false;
    if (nickname != null ? !nickname.equals(user.nickname) : user.nickname != null) return false;
    if (email != null ? !email.equals(user.email) : user.email != null) return false;
    return role == user.role;
  }

  @Override
  public int hashCode() {
    int result = (int) (userId ^ (userId >>> 32));
    result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (role != null ? role.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("User{");
    sb.append("userId=").append(userId);
    sb.append(", nickname='").append(nickname).append('\'');
    sb.append(", email='").append(email).append('\'');
    sb.append(", role=").append(role);
    sb.append('}');
    return sb.toString();
  }
}
