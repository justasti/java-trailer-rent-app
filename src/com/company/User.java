package com.company;

public class User {

  private String username;
  private String password;
  private Order lastOrder;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.lastOrder = null;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public Order getLastOrder() {
    return lastOrder;
  }

  @Override
  public String toString() {
    return "User{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
