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
}
