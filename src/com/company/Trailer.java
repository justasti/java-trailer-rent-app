package com.company;

public class Trailer {

  private String brand;
  private String licensePlate;
  private int maxCapacity;
  private int axles;
  private boolean cover;
  private int parkingSpace;
  private int rentalPrice;
  private boolean rented;

  public Trailer(String brand, String licensePlate, int maxCapacity, int axles, boolean cover, int parkingSpace, int rentalPrice, boolean isRented) {
    this.brand = brand;
    this.licensePlate = licensePlate;
    this.maxCapacity = maxCapacity;
    this.axles = axles;
    this.cover = cover;
    this.parkingSpace = parkingSpace;
    this.rentalPrice = rentalPrice;
    this.rented = isRented;
  }

  public String getBrand() {
    return brand;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public int getMaxCapacity() {
    return maxCapacity;
  }

  public int getAxles() {
    return axles;
  }

  public int getParkingSpace() {
    return parkingSpace;
  }

  public boolean hasCover() {
    return cover;
  }

  public int getRentalPrice() {
    return rentalPrice;
  }

  public boolean isRented() {
    return rented;
  }

  public void setParkingSpace(int parkingSpace) {
    this.parkingSpace = parkingSpace;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public void setHasCover(boolean cover) {
    this.cover = cover;
  }

  public void setRentalPrice(int rentalPrice) {
    this.rentalPrice = rentalPrice;
  }

  public void setRented(boolean rented) {
    this.rented = rented;
  }

  @Override
  public String toString() {
    return"Priekaba: " +
        "\nGamintojas: " + brand +
        "\nValstybinis nr.: " + licensePlate +
        "\nMaksimalus leistinas svoris: " + maxCapacity +
        "\nAšių skaičius: " + axles +
        "\nUždangalas: " + (cover ? "Yra" : "Nėra") +
        "\nStovėjimo vieta aikštelėje: " + parkingSpace +
        "\nNuomos kaina dienai: " + rentalPrice;
  }
}
