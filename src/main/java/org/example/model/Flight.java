package org.example.model;

import java.time.LocalDateTime;

public class Flight {
    private int id;
    private String destination;
    private LocalDateTime dateTime;
    private int availableSeats;

    public Flight(int id, String destination, LocalDateTime dateTime, int availableSeats) {
        this.id = id;
        this.destination = destination;
        this.dateTime = dateTime;
        this.availableSeats = availableSeats;
    }

    public int getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "Flight #" + id + " to " + destination + " at " + dateTime +
                " | Seats available: " + availableSeats;
    }
}
