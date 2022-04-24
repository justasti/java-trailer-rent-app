package com.company;

import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
    DatabaseReader dbr = new DatabaseReader("src/com/company/trailers.txt");

    ArrayList trailers = new ArrayList();

    trailers.add(new Trailer("DOMANTAS", "HM639", 750, 1, false));
    trailers.add(new Trailer("MAX", "HM639", 11000, 3, false));

//    dbr.addToFile(trailers);
    System.out.println(dbr.getAllTrailers());
  }
}
