package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDatabase extends DatabaseReader {
  public UserDatabase(String path) {
    super(path);
  }

  ArrayList<User> users = new ArrayList<>();

  protected ArrayList getAllEntries() {
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
      System.out.println(e);
    }
    return users;
  }


}
