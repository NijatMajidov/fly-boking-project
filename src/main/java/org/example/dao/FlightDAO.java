package org.example.dao;

import org.example.model.Flight;
import org.example.util.FileUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {
    private List<Flight> flights = new ArrayList<>();
    private final String flightFile = "data/flights.txt";

    public FlightDAO() {
        loadFlights();
    }

    private void loadFlights() {
        List<String> lines = FileUtil.readFile(flightFile);
        for (String line : lines) {
            String[] parts = line.split(";");
            int id = Integer.parseInt(parts[0]);
            String destination = parts[1];
            LocalDateTime dateTime = LocalDateTime.parse(parts[2]);
            int availableSeats = Integer.parseInt(parts[3]);
            flights.add(new Flight(id, destination, dateTime, availableSeats));
        }
    }

    public void saveFlights() {
        List<String> lines = new ArrayList<>();
        for (Flight flight : flights) {
            lines.add(flight.getId() + ";" + flight.getDestination() + ";" +
                    flight.getDateTime() + ";" + flight.getAvailableSeats());
        }
        FileUtil.writeFile(flightFile, lines);
    }

    public List<Flight> getAllFlights() {
        return flights;
    }

    public Flight getFlightById(int id) {
        return flights.stream()
                .filter(f -> f.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Flight> getFlightsByDestinationAndDate(String destination, LocalDateTime date, int passengers) {
        return flights.stream()
                .filter(f -> f.getDestination().equalsIgnoreCase(destination)
                        && f.getDateTime().toLocalDate().equals(date.toLocalDate())
                        && f.getAvailableSeats() >= passengers)
                .toList();
    }

    public void updateFlight(Flight flight) {
        saveFlights();
    }
}
