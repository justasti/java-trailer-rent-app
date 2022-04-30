package com.company.database;

import com.company.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TrailerDatabase extends DatabaseReader {


  public TrailerDatabase(String path) {
    super(path);
  }

  ArrayList<Trailer> trailers = new ArrayList<>();

  @Override
  public ArrayList getAllEntries() {
    try {
      File file = new File(path);
      Scanner sc = new Scanner(file);
      while (sc.hasNextLine()) {
        String brand = sc.nextLine();
        String licensePlate = sc.nextLine();
        int maxCapacity = sc.nextInt();
        sc.nextLine();
        int axles = sc.nextInt();
        sc.nextLine();
        boolean cover = sc.nextBoolean();
        sc.nextLine();
        int parkingSpace = sc.nextInt();
        sc.nextLine();
        int rentalPrice = sc.nextInt();
        sc.nextLine();
        boolean isRented = sc.nextBoolean();
        sc.nextLine();
        sc.nextLine();

        trailers.add(new Trailer(brand, licensePlate, maxCapacity, axles, cover, parkingSpace, rentalPrice, isRented));
      }
    } catch (
        FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
    return trailers;
  }

  public Trailer getTrailer(int weight) {
    for (Trailer trailer : trailers) {
      if (trailer.getMaxCapacity() == weight && !trailer.isRented()) {
        return trailer;
      }
    }
    return null;
  }

  public Trailer getTrailer(String licensePlate) {
    for (Trailer trailer : trailers) {
      if (trailer.getLicensePlate().equalsIgnoreCase(licensePlate)) {
        return trailer;
      }
    }
    return null;
  }

  public void updateTrailer(Trailer trailer) throws IOException {
    FileWriter fw = new FileWriter(path);
    PrintWriter printer = new PrintWriter(fw);

    ArrayList<Trailer> trailers = getAllEntries();

    updateTrailerStatus(trailer, trailers);

    for (Trailer trailerToWrite : trailers) {
      printer.println(trailerToWrite.getBrand());
      printer.println(trailerToWrite.getLicensePlate());
      printer.println(trailerToWrite.getMaxCapacity());
      printer.println(trailerToWrite.getAxles());
      printer.println(trailerToWrite.hasCover());
      printer.println(trailerToWrite.getParkingSpace());
      printer.println(trailerToWrite.getRentalPrice());
      printer.println(trailerToWrite.isRented());
      printer.println();
    }
    printer.close();
  }

  private void updateTrailerStatus(Trailer trailer, ArrayList<Trailer> trailers) {
    for (Trailer trailerToUpdate : trailers) {
      if (trailerToUpdate.getLicensePlate().equalsIgnoreCase(trailer.getLicensePlate())) {
        trailerToUpdate.setRented(trailer.isRented());
      }
    }
  }
}
