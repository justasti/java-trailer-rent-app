package com.company;

import com.company.database.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    TrailerDatabase trailersDB = new TrailerDatabase("src/com/company/database/trailers.txt");
    UserDatabase usersDB = new UserDatabase("src/com/company/database/users.txt");
    OrderDatabase ordersDB = new OrderDatabase("src/com/company/database/orders.txt");
    Scanner sc = new Scanner(System.in);


    String chosenMenuOption = "";
    String userSelection = "";

    ArrayList<Trailer> allTrailers = trailersDB.getAllEntries();
    ArrayList<User> allUsers = usersDB.getAllEntries();
    ArrayList<Order> allOrders = ordersDB.getAllEntries();
    while (true) {
      printStartMenu();

      System.out.print("Pasirinkite meniu punktą: ");
      chosenMenuOption = sc.nextLine();

      switch (chosenMenuOption) {
        case "1":
          System.out.print("Įveskite prisijungimo vardą: ");
          String username = sc.nextLine();
          System.out.print("Įveskite slaptažodį: ");
          String password = sc.nextLine();

          User userToLogin = usersDB.getUser(username, password);
          if (userToLogin == null) {
            System.out.println("Neteisingi prisijungimo duomenys\n");
          } else {
            System.out.println("Sėkmingai prisijungta\n");
            while (true) {
              printUserMenu();
              System.out.print("Pasirinkite meniu punktą: ");
              userSelection = sc.nextLine();
              switch (userSelection) {
                case "1":
                  System.out.println("-------------------");
                  printAvailableTrailers(allTrailers);
                  break;
                case "2":
                  printTrailersMenu();
                  System.out.print("Pasirinkite meniu punktą: ");
                  String selectedTrailer = sc.nextLine();
                  switch (selectedTrailer) {
                    case "1":
                      Trailer trailerToRent = trailersDB.getTrailer(750);
                      if (trailerToRent != null) {
                        System.out.println("Pasirinkta priekaba: \n" + trailerToRent);
                        confirmChoiceMenu();
                        String confirmChoice = sc.nextLine();
                        if (confirmChoice.equals("1")) {

                          trailerToRent.setRented(true);
                          Order newOrder = new Order(allOrders.size() + 1, userToLogin.getUsername(), LocalDate.now(), trailerToRent);
                          allOrders.add(newOrder);
                          userToLogin.setLastOrder(newOrder.getId());

                          rewriteAllFiles(trailersDB, allTrailers, ordersDB, allOrders, usersDB, allUsers);

                        } else if (confirmChoice.equals("2")) {
                          break;
                        } else {
                          System.out.println("Neatpažinta įvestis\n");
                        }
                      } else {
                        System.out.println("NO");
                      }
                      break;
                  }
                case "3":
                  break;
                case "4":
                  Order lastOrder = usersDB.getUserLastOrder(userToLogin, allOrders);
                  if (lastOrder != null) {
                    System.out.println(lastOrder);
                  } else {
                    System.out.println("Vartotojas užsakymų dar neatliko!");
                  }
              }
            }
          }

          break;
        case "2":
          System.out.print("Įveskite vartotojo vardą: ");
          String newUsername = sc.nextLine();
          System.out.print("Įveskite slaptažodį: ");
          String newPassword = sc.nextLine();
          if (usersDB.addUser(new User(newUsername, newPassword))) {
            System.out.println("Registracija sėkminga!");
          } else {
            System.out.println("Toks vartotojas jau egzistuoja");
          }
          usersDB.getAllEntries();
          break;
        case "9":
          System.out.println("9");
          break;
        case "0":
          return;
        default:
          System.out.println("Neatpažinta įvestis\n");
      }
    }

  }

  private static void printStartMenu() {
    System.out.println("1. Prisijungti");
    System.out.println("2. Registruotis");
    System.out.println("9. Administratoriaus meniu");
    System.out.println("0. Baigti darbą\n");
  }

  private static void printUserMenu() {
    System.out.println("1. Peržiūrėti priekabų likutį");
    System.out.println("2. Išsinuomoti priekabą");
    System.out.println("3. Grąžinti priekabą");
    System.out.println("4. Peržiūrėti paskutinį savo užsakymą");
    System.out.println("0. Atsijungti\n");
  }

  private static void printAvailableTrailers(ArrayList<Trailer> allTrailers) {
    if (allTrailers.size() == 0 || allTrailers.stream().allMatch(Trailer::isRented)) {
      System.out.println("Atleiskite, šiuo metu laisvų priekabų neturime!\n");
    } else {
      for (Trailer trailer : allTrailers) {
        if (!trailer.isRented()) {
          System.out.println(trailer);
          System.out.println("-------------------");
        }
      }
    }
  }

  private static void printTrailersMenu() {
    System.out.println("1. Priekaba iki 750kg");
    System.out.println("2. Priekaba iki 1500kg");
    System.out.println("3. Platforminė priekaba");
    System.out.println("0. Grįžti\n");
  }

  private static void confirmChoiceMenu() {
    System.out.println("1. Patvirtinti pasirinkimą");
    System.out.println("2. Grįžti atgal\n");
  }

  private static void rewriteAllFiles(TrailerDatabase trailersDB, ArrayList<Trailer> allTrailers, OrderDatabase ordersDB, ArrayList<Order> allOrders, UserDatabase usersDB, ArrayList<User> allUsers) {
    trailersDB.addTrailersToFile(allTrailers);
    ordersDB.addOrdersToFile(allOrders);
    usersDB.addUsersToFile(allUsers);
  }


}
