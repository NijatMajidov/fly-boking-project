package org.example.controller;


import org.example.model.Flight;
import org.example.service.FlightService;
import java.time.LocalDateTime;
import java.util.List;

public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    public Flight getFlightById(int id) {
        return flightService.getFlightById(id);
    }

    public List<Flight> searchFlights(String destination, LocalDateTime date, int passengers) {
        return flightService.searchFlights(destination, date, passengers);
    }
}
