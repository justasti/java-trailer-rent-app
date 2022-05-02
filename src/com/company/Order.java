package com.company;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Order {
  private String username;
  private LocalDate startingDate;
  private String licencePlate;
  private boolean returned;
  private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");


  public Order(String username, LocalDate startingDate, String licencePlate, boolean returned) {
    this.username = username;
    this.startingDate = startingDate;
    this.licencePlate = licencePlate;
    this.returned = returned;

  }


  public String getUsername() {
    return username;
  }

  public String getStartingDate() {
    return startingDate.format(dtf);
  }

  public String getLicencePlate() {
    return licencePlate;
  }

  public boolean isReturned() {
    return returned;
  }

  public void setReturned(boolean returned) {
    this.returned = returned;
  }

  @Override
  public String toString() {
    return "Vartotojas: " + username +
        " Pradžios data: " + startingDate +
        " Priekaba: " + licencePlate +
        " Statusas: " + (isReturned() ? "Užbaigtas" : "Aktyvus");
  }
}
