package com.company.database;

import com.company.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderDatabase extends DatabaseReader {
  public OrderDatabase(String path) {
    super(path);
  }

  ArrayList<Order> orders = new ArrayList<>();

  @Override
  public ArrayList getAllEntries() {
    try {
      File file = new File(path);
      Scanner sc = new Scanner(file);

      while (sc.hasNextLine()) {
        String username = sc.nextLine();
        String password = sc.nextLine();
        String date = sc.nextLine();
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

        orders.add(new Order(new User(username, password), LocalDate.parse(date), new Trailer(brand, licensePlate, maxCapacity, axles, cover, parkingSpace, rentalPrice, isRented)));
      }
    } catch (
        FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
    return orders;
  }

  public void addOrder(User user) {
    try {
      FileWriter fw = new FileWriter(path, true);
      PrintWriter printer = new PrintWriter(fw);
      printer.println(user.getUsername());
      printer.println(user.getPassword());
      printer.println();

      printer.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public void addOrdersToFile(ArrayList<Order> orders) {

    try {
      FileWriter fw = new FileWriter(path);
      PrintWriter printer = new PrintWriter(fw);

      for (Order order : orders) {
        printer.println(order.getUser().getUsername());
        printer.println(order.getUser().getPassword());
        printer.println(order.getDate());
        printer.println(order.getTrailer().getBrand());
        printer.println(order.getTrailer().getLicensePlate());
        printer.println(order.getTrailer().getMaxCapacity());
        printer.println(order.getTrailer().getAxles());
        printer.println(order.getTrailer().hasCover());
        printer.println(order.getTrailer().getParkingSpace());
        printer.println(order.getTrailer().getRentalPrice());
        printer.println(order.getTrailer().isRented());
        printer.println();
      }

      printer.close();
    } catch (IOException e) {

      System.out.println(e);
    }
  }
}
