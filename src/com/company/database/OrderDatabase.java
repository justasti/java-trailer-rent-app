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


  @Override
  public ArrayList<Order> getAllEntries() {
    ArrayList<Order> orders = new ArrayList<>();
    try {
      File file = new File(path);
      Scanner sc = new Scanner(file);

      while (sc.hasNextLine()) {
        String username = sc.nextLine();
        String date = sc.nextLine();
        String licensePlate = sc.nextLine();
        boolean returned = sc.nextBoolean();
        sc.nextLine();
        sc.nextLine();

        orders.add(new Order(username, LocalDate.parse(date), licensePlate, returned));
      }
    } catch (
        FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
    return orders;
  }

  public void addOrdersToFile(ArrayList<Order> orders) {

    try {
      FileWriter fw = new FileWriter(path);
      PrintWriter printer = new PrintWriter(fw);

      for (Order order : orders) {
        printer.println(order.getUsername());
        printer.println(order.getStartingDate());
        printer.println(order.getLicencePlate());
        printer.println(order.isReturned());

        printer.println();
      }

      printer.close();
    } catch (IOException e) {

      System.out.println(e.getMessage());
    }
  }

  public void updateOrder(ArrayList<Order> allOrders) throws IOException {
    FileWriter fw = new FileWriter(path);
    PrintWriter printer = new PrintWriter(fw);


    for (Order orderToWrite : allOrders) {
      printer.println(orderToWrite.getUsername());
      printer.println(orderToWrite.getStartingDate());
      printer.println(orderToWrite.getLicencePlate());
      printer.println(orderToWrite.isReturned());
      printer.println();
    }
    printer.close();
  }

}
