package com.company;

public class Trailer {

    private String brand;
    private String licensePlate;
    private int maxCapacity;
    private int axles;
    private boolean cover;

    public Trailer(String brand, String licensePlate, int maxCapacity, int axles, boolean cover) {
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.maxCapacity = maxCapacity;
        this.axles = axles;
        this.cover = cover;
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

    public boolean hasCover() {
        return cover;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setHasCover(boolean cover) {
        this.cover = cover;
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
