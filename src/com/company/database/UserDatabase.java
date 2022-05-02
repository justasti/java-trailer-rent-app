package com.company.database;

import com.company.Order;
import com.company.User;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDatabase extends DatabaseReader {
  public UserDatabase(String path) {
    super(path);
  }

  ArrayList<User> users = new ArrayList<>();

  public ArrayList<User> getAllEntries() {
    try {
      File file = new File(path);
      Scanner sc = new Scanner(file);

      while (sc.hasNextLine()) {
        String username = sc.nextLine();
        String password = sc.nextLine();
        sc.nextLine();

        users.add(new User(username, password));
      }
    } catch (
        FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
    return users;
  }

  public User getUser(String username, String password) {
    for (User user : users) {
      if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
        return user;
      }
    }
    return null;
  }

  private boolean checkForExistingUser(String username) {
    for (User user : users) {
      if (username.equals(user.getUsername())) {
        return true;
      }
    }
    return false;
  }

  public boolean addUser(User user) {
    if (!checkForExistingUser(user.getUsername())) {
      try {
        FileWriter fw = new FileWriter(path, true);
        PrintWriter printer = new PrintWriter(fw);

        printer.println(user.getUsername());
        printer.println(user.getPassword());
        printer.println();

        printer.close();
        return true;
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
    return false;
  }

  public ArrayList<Order> getUserOrders(User user, ArrayList<Order> allOrders) {
    ArrayList<Order> selectedUserOrders = new ArrayList<>();
    for (Order order : allOrders) {
      if (order.getUsername().equals(user.getUsername())) {
        selectedUserOrders.add(order);
      }
    }
    if (selectedUserOrders.size() > 0) {
      return selectedUserOrders;
    } else {
      return null;
    }
  }



}
