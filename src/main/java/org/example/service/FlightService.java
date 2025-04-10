package org.example.service;

import org.example.dao.FlightDAO;
import org.example.model.Flight;
import java.time.LocalDateTime;
import java.util.List;

public class FlightService {
    private final FlightDAO flightDAO;

    public FlightService(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    public List<Flight> getAllFlights() {
        return flightDAO.getAllFlights();
    }

    public Flight getFlightById(int id) {
        return flightDAO.getFlightById(id);
    }

    public List<Flight> searchFlights(String destination, LocalDateTime date, int passengers) {
        return flightDAO.getFlightsByDestinationAndDate(destination, date, passengers);
    }

    public void decreaseSeats(Flight flight, int number) {
        flight.setAvailableSeats(flight.getAvailableSeats() - number);
        flightDAO.updateFlight(flight);
    }
}