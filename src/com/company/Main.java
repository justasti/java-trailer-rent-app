package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    TrailerDatabase trailersDB = new TrailerDatabase("src/com/company/trailers.txt");
    UserDatabase usersDB = new UserDatabase("src/com/company/users.txt");
    Scanner sc = new Scanner(System.in);

    String chosenMenuOption = "";
    String userSelection = "";

    while (true) {
      ArrayList<Trailer> allTrailers = trailersDB.getAllEntries();
      ArrayList<User> allUsers = usersDB.getAllEntries();

      printStartMenu();

      System.out.print("Pasirinkite skaičių: ");
      chosenMenuOption = sc.nextLine();

      switch (chosenMenuOption) {
        case "1":
          System.out.print("Įveskite prisijungimo vardą: ");
          String username = sc.nextLine();
          System.out.print("Įveskite slaptažodį: ");
          String password = sc.nextLine();

          User userToLogin = usersDB.getUser(username, password);
          if (userToLogin == null) {
            System.out.println("Neteisingi prisijungimo duomenys");
          } else {
            System.out.println("Sėkmingai prisijungta");
            printUserMenu();
            System.out.print("Pasirinkite skaičių: ");
            while (true) {
              userSelection = sc.nextLine();
              switch (userSelection) {
                case "1":
                  printAvailableTrailers(allTrailers);
                  break;
              }
            }
          }

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

  private static void printUserMenu() {
    System.out.println("1. Peržiūrėti priekabų likutį");
    System.out.println("2. Išsinuomoti priekabą");
    System.out.println("3. Grąžinti priekabą");
    System.out.println("4. Peržiūrėti paskutinį savo užsakymą");
    System.out.println("0. Grįžti");
  }

  private static void printAvailableTrailers(ArrayList<Trailer> allTrailers) {
    for (Trailer trailer : allTrailers) {
      if (!trailer.isRented()) {
        System.out.println(trailer);
        System.out.println("-------------------");
      }
    }
  }
}
