package com.company;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Order {
  private String username;
  private LocalDate date;
  private Trailer trailer;
  private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private int id = 1;
  private static int nextId = 1;

  public Order(int id, String username, LocalDate date, Trailer trailer) {
    this.username = username;
    this.date = date;
    this.trailer = trailer;
    this.id = nextId;
    nextId++;
  }


  public String getUsername() {
    return username;
  }

  public String getDate() {
    return date.format(dtf);
  }

  public Trailer getTrailer() {
    return trailer;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Vartotojas: " + username +
        " Pradžios data: " + date +
        " Priekaba: " + trailer.getLicensePlate();
  }
}
