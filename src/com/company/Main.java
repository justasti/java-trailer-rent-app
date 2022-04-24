package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    DatabaseReader trailersDB = new TrailerDatabase("src/com/company/trailers.txt");
    DatabaseReader usersDB = new UserDatabase("src/com/company/users.txt");
    Scanner sc = new Scanner(System.in);

    String chosenMenuOption = "";

    while (true) {
      ArrayList allTrailers = trailersDB.getAllEntries();
      ArrayList allUsers = usersDB.getAllEntries();
      System.out.println(allTrailers);
      System.out.println(allUsers);

      printStartMenu();

      System.out.print("Pasirinkite skaičių: ");
      chosenMenuOption = sc.nextLine();

      switch (chosenMenuOption) {
        case "1":
          System.out.println("1");
          break;
        case "2":
          System.out.println("2");
          break;
        case "9":
          System.out.println("9");
          break;
        case "0":
          return;
        default:
          System.out.println("Neatpažinta įvestis");
      }
    }

  }
  private static void printStartMenu() {
    System.out.println("1. Prisijungti");
    System.out.println("2. Registruotis");
    System.out.println("9. Administratoriaus meniu");
    System.out.println("0. Baigti darbą");
  }
}
