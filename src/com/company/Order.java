package com.company;
import com.company.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Order {
  User user;
  LocalDate date;
  Trailer trailer;

  public Order(User user, LocalDate date, Trailer trailer) {
    this.user = user;
    this.date = date;
    this.trailer = trailer;
  }

  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public User getUser() {
    return user;
  }

  public String getDate() {
    return date.format(dtf);
  }

  public Trailer getTrailer() {
    return trailer;
  }

  @Override
  public String toString() {
    return "Vartotojas: " + user +
        " Pradžios data: " + date +
        " Priekaba: " + trailer.getLicensePlate();
  }
}
