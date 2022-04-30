package com.company;

public class User {

  private String username;
  private String password;
  private int lastOrder;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public int getLastOrder() {
    return lastOrder;
  }

  public void setLastOrder(int lastOrder) {
    this.lastOrder = lastOrder;
  }



  @Override
  public String toString() {
    return username;
  }
}
