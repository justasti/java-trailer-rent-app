package com.company;

import java.util.Date;

public class Order {
  User user;
  Date date;
  Trailer trailer;

  public Order(User user, Date date, Trailer trailer) {
    this.user = user;
    this.date = date;
    this.trailer = trailer;
  }

  public User getUser() {
    return user;
  }

  public Date getDate() {
    return date;
  }

  public Trailer getTrailer() {
    return trailer;
  }
}
