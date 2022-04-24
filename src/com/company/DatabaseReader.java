package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class DatabaseReader {

  private String path;

  public DatabaseReader(String path) {

    this.path = path;
  }

  public ArrayList getAllTrailers() {
    ArrayList<Trailer> trailers = new ArrayList<>();

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
        sc.nextLine();

        trailers.add(new Trailer(brand, licensePlate, maxCapacity, axles, cover));
      }
    } catch (FileNotFoundException e) {
      System.out.println(e);
    }
    return trailers;
  }


  public void addToFile(ArrayList<Trailer> trailers) {
    try {
      FileWriter fw = new FileWriter(path);
      PrintWriter printer = new PrintWriter(fw);

      for (Trailer trailer : trailers) {
        printer.println(trailer.getBrand());
        printer.println(trailer.getLicensePlate());
        printer.println(trailer.getMaxCapacity());
        printer.println(trailer.getAxles());
        printer.println(trailer.hasCover());
        printer.println();
      }
      printer.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }

}
