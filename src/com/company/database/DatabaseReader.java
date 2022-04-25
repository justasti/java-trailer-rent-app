package com.company.database;
import com.company.*;
import java.io.*;
import java.util.ArrayList;


public abstract class DatabaseReader {

  protected String path;

  public DatabaseReader(String path) {

    this.path = path;
  }

  protected abstract ArrayList getAllEntries();

  public void addTrailersToFile( ArrayList<Trailer> trailers) {

    try {
      FileWriter fw = new FileWriter(path);
      PrintWriter printer = new PrintWriter(fw);

      for (Trailer trailer : trailers) {
        printer.println(trailer.getBrand());
        printer.println(trailer.getLicensePlate());
        printer.println(trailer.getMaxCapacity());
        printer.println(trailer.getAxles());
        printer.println(trailer.hasCover());
        printer.println(trailer.getParkingSpace());
        printer.println(trailer.getRentalPrice());
        printer.println(trailer.isRented());
        printer.println();
      }

      printer.close();
    } catch (IOException e) {

      System.out.println(e);
    }
  }
}
