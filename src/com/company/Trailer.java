package com.company;

public class Trailer {

  private String brand;
  private String licensePlate;
  private int maxCapacity;
  private int axles;
  private boolean cover;
  private int parkingSpace;
  private int rentalPrice;

  public Trailer(String brand, String licensePlate, int maxCapacity, int axles, boolean cover, int parkingSpace, int rentalPrice) {
    this.brand = brand;
    this.licensePlate = licensePlate;
    this.maxCapacity = maxCapacity;
    this.axles = axles;
    this.cover = cover;
    this.parkingSpace = parkingSpace;
    this.rentalPrice = rentalPrice;
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

  @Override
  public String toString() {
    return "Trailer{" +
        "brand='" + brand + '\'' +
        ", licensePlate='" + licensePlate + '\'' +
        ", maxCapacity=" + maxCapacity +
        ", axles=" + axles +
        ", cover=" + cover +
        '}';
  }
}
