package org.example.helper;

import org.example.controller.BookingController;
import org.example.controller.FlightController;
import org.example.dao.BookingDAO;
import org.example.dao.FlightDAO;
import org.example.model.Booking;
import org.example.model.Flight;
import org.example.model.Passenger;
import org.example.service.BookingService;
import org.example.service.FlightService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private final Scanner scanner = new Scanner(System.in);
    private final FlightController flightController;
    private final BookingController bookingController;

    public ConsoleApp() {
        FlightDAO flightDAO = new FlightDAO();
        BookingDAO bookingDAO = new BookingDAO();
        FlightService flightService = new FlightService(flightDAO);
        BookingService bookingService = new BookingService(bookingDAO, flightService);
        this.flightController = new FlightController(flightService);
        this.bookingController = new BookingController(bookingService);
    }

    public void start() {
        System.out.println("Welcome to the Plane Ticket Booking System ✈️");

        while (true) {
            printMenu();
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1" -> showOnlineBoard();
                    case "2" -> showFlightInfo();
                    case "3" -> searchAndBookFlight();
                    case "4" -> cancelBooking();
                    case "5" -> myBookings();
                    case "6" -> exitApp();
                    default -> System.out.println(" Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Online-board (flights in the next 24h)");
        System.out.println("2. Show flight info by ID");
        System.out.println("3. Search and book a flight");
        System.out.println("4. Cancel booking");
        System.out.println("5. My flights");
        System.out.println("6. Exit");
        System.out.print("Choose option: ");
    }

    private void showOnlineBoard() {
        System.out.println("\nUpcoming flights from Kiev (next 24 hours):");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusHours(24);
        List<Flight> allFlights = flightController.getAllFlights();
        allFlights.stream()
                .filter(f -> f.getDateTime().isAfter(now) && f.getDateTime().isBefore(tomorrow))
                .forEach(System.out::println);
    }

    private void showFlightInfo() {
        System.out.print("Enter flight ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Flight flight = flightController.getFlightById(id);
        if (flight != null) {
            System.out.println("Flight Info: " + flight);
        } else {
            System.out.println(" Flight not found.");
        }
    }

    private void searchAndBookFlight() {
        String destination;
        LocalDate date;
        int count;

        while (true) {
            try {
                System.out.print("Enter destination: ");
                destination = scanner.nextLine();

                System.out.print("Enter date (yyyy-mm-dd): ");
                date = LocalDate.parse(scanner.nextLine());

                System.out.print("Enter number of passengers: ");
                count = Integer.parseInt(scanner.nextLine());

                break;
            } catch (Exception e) {
                System.out.println("⚠️ Invalid input. Please try again.");
            }
        }
        List<Flight> results = flightController.searchFlights(destination, date.atStartOfDay(), count);
        if (results.isEmpty()) {
            System.out.println(" No flights found.");
            return;
        }

        System.out.println("\nAvailable Flights:");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i));
        }
        System.out.println("0. Return to main menu");

        Flight selectedFlight = null;
        while (selectedFlight == null) {
            System.out.print("Select a flight: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 0) return;
                if (choice < 1 || choice > results.size()) {
                    System.out.println("Invalid choice. Please try again.");
                } else {
                    selectedFlight = results.get(choice - 1);
                }
            } catch (Exception e) {
                System.out.println("⚠️ Invalid input. Please enter a number.");
            }
        }

        List<Passenger> passengers = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            while (true) {
                System.out.print("Enter first name of passenger #" + i + ": ");
                String firstName = scanner.nextLine().trim();

                System.out.print("Enter last name of passenger #" + i + ": ");
                String lastName = scanner.nextLine().trim();

                if (!firstName.isEmpty() && !lastName.isEmpty()) {
                    passengers.add(new Passenger(firstName, lastName));
                    break;
                } else {
                    System.out.println("⚠️ Names cannot be empty. Try again.");
                }
            }
        }

        bookingController.createBooking(selectedFlight, passengers);
        System.out.println("✅ Booking confirmed!");
    }

    private void cancelBooking() {
        System.out.print("Enter booking ID to cancel: ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean success = bookingController.cancelBooking(id);
        if (success) {
            System.out.println(" Booking cancelled.");
        } else {
            System.out.println(" Booking not found.");
        }
    }

    private void myBookings() {
        System.out.print("Enter your full name: ");
        String name = scanner.nextLine();
        List<Booking> bookings = bookingController.getBookingsByPassenger(name);
        if (bookings.isEmpty()) {
            System.out.println(" No bookings found.");
        } else {
            bookings.forEach(System.out::println);
        }
    }

    private void exitApp() {
        System.out.println("Goodbye! ");
        System.exit(0);
    }
}