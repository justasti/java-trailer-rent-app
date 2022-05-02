package com.company;

import com.company.database.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException {
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
              if (userToLogin.getUsername().equalsIgnoreCase("admin")) {
                printAdminMenu();
                System.out.print("Pasirinkite meniu punktą: ");
                userSelection = sc.nextLine();

                switch (userSelection) {
                  case "1":
                    System.out.print("Įveskite priekabos gamintoją: ");
                    String brand = sc.nextLine();
                    System.out.print("Įveskite priekabos valstybinį numerį: ");
                    String licensePlate = sc.nextLine();
                    System.out.print("Įveskite priekabos maksimalią apkrovą (kg): ");
                    int maxCapacity = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Įveskite priekabos ašių skaičių: ");
                    int axles = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Ar priekaba turi tentą? (T/N): ");
                    String cover = sc.nextLine();
                    System.out.print("Įveskite priekabos nuomos kainą dienai: ");
                    int rentalPrice = sc.nextInt();
                    sc.nextLine();
                    addNewTrailer(trailersDB, allTrailers, brand, licensePlate, maxCapacity, axles, cover, rentalPrice);
                    break;
                  case "2":
                    System.out.print("Įveskite priekabos valstybinį numerį: ");
                    String licensePlateToRemove = sc.nextLine();
                    removeTrailer(trailersDB, licensePlateToRemove);
                    break;
                  case "3":
                    System.out.println("Pakeisti priekabos valst. nr");
                    break;
                  case "0":
                    return;
                  default:
                    System.out.println("Neatpažinta įvestis\n");
                    break;
                }

              } else {
                printUserMenu();
                System.out.print("Pasirinkite meniu punktą: ");
                userSelection = sc.nextLine();

                switch (userSelection) {
                  case "1":
                    System.out.println("-------------------");
                    printAvailableTrailers(allTrailers);
                    break;

                  case "2":
                    Trailer trailerToRent = null;
                    printTrailersMenu();
                    System.out.print("Pasirinkite meniu punktą: ");
                    String selectedTrailer = sc.nextLine();
                    switch (selectedTrailer) {

                      case "1":
                        trailerToRent = trailersDB.getTrailer(750);
                        rentTrailerByWeigth(trailersDB, ordersDB, sc, allOrders, userToLogin, trailerToRent);
                        break;

                      case "2":
                        trailerToRent = trailersDB.getTrailer(1500);
                        rentTrailerByWeigth(trailersDB, ordersDB, sc, allOrders, userToLogin, trailerToRent);
                        break;

                      case "3":
                        trailerToRent = trailersDB.getTrailer(3500);
                        rentTrailerByWeigth(trailersDB, ordersDB, sc, allOrders, userToLogin, trailerToRent);
                        break;
                      default:
                        System.out.println("Neatpažinta įvestis.\n");
                    }
                    break;

                  case "3":
                    System.out.print("Įveskite priekabos valstybinį numerį: ");
                    String licensePlate = sc.nextLine();
                    Trailer trailerToReturn = trailersDB.getTrailer(licensePlate);
                    if (trailerToReturn == null) {
                      System.out.println("Tokia priekaba neegzistuoja.");
                    } else if (!trailerToReturn.isRented()) {
                      System.out.println("Priekaba nėra išnuomota.");
                    } else {
                      returnTrailer(trailerToReturn, userToLogin, ordersDB, trailersDB);
                      System.out.printf("Priekaba sėkmingai grąžinta. Prašome pastatyti priekabą į %d-ą stovėjimo aikštelės vietą\n", trailerToReturn.getParkingSpace());
                    }
                    break;

                  case "4":
                    ArrayList<Order> allUserOrders = usersDB.getUserOrders(userToLogin, allOrders);
                    if (allUserOrders != null) {
                      for (Order order : allUserOrders) {
                        System.out.println(order);
                      }
                      ;
                    } else {
                      System.out.println("Vartotojas užsakymų dar neatliko!");
                    }
                    break;

                  case "0":
                    return;
                  default:
                    System.out.println("Neatpažinta įvestis\n");
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
            System.out.println("Registracija sėkminga!\n");
          } else {
            System.out.println("Toks vartotojas jau egzistuoja\n");
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

  private static void addNewTrailer(TrailerDatabase trailersDB, ArrayList<Trailer> allTrailers, String brand, String licensePlate, int maxCapacity, int axles, String cover, int rentalPrice) throws IOException {
    Trailer newTrailer = new Trailer(brand, licensePlate,
        maxCapacity, axles, cover.equalsIgnoreCase("T"),
        allTrailers.size() + 1, rentalPrice, false);
    allTrailers.add(newTrailer);
    trailersDB.updateTrailer(newTrailer);
    System.out.println("Nauja priekaba sėkmingai pridėta.\n");
  }

  private static void removeTrailer(TrailerDatabase trailersDB, String licensePlateToRemove) throws IOException {
    Trailer trailerToRemove = trailersDB.getTrailer(licensePlateToRemove);
    if (trailerToRemove != null) {
      trailersDB.removeTrailer(trailerToRemove);
      System.out.println("Priekaba sėkmingai panaikinta.\n");
    } else {
      System.out.println("Priekaba su tokiu valstybiniu numeriu nerasta.\n");
    }
  }

  private static void printAdminMenu() {
    System.out.println("1. Įvesti naują priekabą");
    System.out.println("2. Panaikinti priekabą");
    System.out.println("3. Pakeisti priekabos valstybinį numerį");
    System.out.println("0. Atsijungti\n");
  }

  private static void rentTrailerByWeigth(TrailerDatabase trailersDB, OrderDatabase ordersDB, Scanner sc, ArrayList<Order> allOrders, User userToLogin, Trailer trailerToRent) {
    if (trailerToRent != null) {
      System.out.println("Pasirinkta priekaba: \n" + trailerToRent);
      confirmChoiceMenu();
      String confirmChoice = sc.nextLine();
      if (confirmChoice.equals("1")) {

        trailerToRent.setRented(true);
        Order newOrder = new Order(userToLogin.getUsername(), LocalDate.now(), trailerToRent.getLicensePlate(), false);
        allOrders.add(newOrder);
        try {
          trailersDB.updateTrailer(trailerToRent);
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
        ordersDB.addOrdersToFile(allOrders);

      } else if (confirmChoice.equals("2")) {
        return;
      } else {
        System.out.println("Neatpažinta įvestis\n");
      }
    } else {
      System.out.println("Šiuo metu laisvų priekabų pasirinktoje kategorijoje neturime.\n");
    }
  }

  private static void printStartMenu() {
    System.out.println("1. Prisijungti");
    System.out.println("2. Registruotis");
    System.out.println("0. Baigti darbą\n");
  }

  private static void printUserMenu() {
    System.out.println("1. Peržiūrėti priekabų likutį");
    System.out.println("2. Išsinuomoti priekabą");
    System.out.println("3. Grąžinti priekabą");
    System.out.println("4. Peržiūrėti savo užsakymus");
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
    System.out.print("Įveskite pasirinkimą: ");
  }

  public static void returnTrailer(Trailer trailer, User user, OrderDatabase orderDatabase, TrailerDatabase trailerDatabase) throws IOException {
    trailer.setRented(false);

    ArrayList<Order> allOrders = orderDatabase.getAllEntries();
    for (Order order : allOrders) {
      if (!order.isReturned()
          && order.getLicencePlate().equalsIgnoreCase(trailer.getLicensePlate())
          && order.getUsername().equalsIgnoreCase(user.getUsername())) {
        trailerDatabase.updateTrailer(trailer);
        order.setReturned(true);
        orderDatabase.updateOrder(allOrders);
        return;
      }
    }
    System.out.println("Priekabos grąžinti neįmanoma\n");
  }
}
