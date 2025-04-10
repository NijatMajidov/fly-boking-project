package org.example.helper;

import org.example.controller.FlightController;
import org.example.dao.FlightDAO;
import org.example.model.Flight;
import org.example.service.FlightService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private final Scanner scanner = new Scanner(System.in);
    private final FlightController flightController;
    //private final BookingController bookingController;

    public ConsoleApp() {
        FlightDAO flightDAO = new FlightDAO();
       // BookingDAO bookingDAO = new BookingDAO();
        FlightService flightService = new FlightService(flightDAO);
        //BookingService bookingService = new BookingService(bookingDAO, flightService);
        this.flightController = new FlightController(flightService);
        //this.bookingController = new BookingController(bookingService);
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
                   //case "3" -> searchAndBookFlight();
                    // case "4" -> cancelBooking();
                   // case "5" -> myBookings();
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


    private void exitApp() {
        System.out.println("Goodbye! ");
        System.exit(0);
    }
}