package org.example.model;
 
import java.util.List; 
 
public class Booking { 
 private int id; 
 private int flightId; 
 private List passengers; 
 
 public Booking(int id, int flightId, List passengers) { 
 this.id = id; 
 this.flightId = flightId; 
 this.passengers = passengers; 
 } 
 
 public int getId() { 
 return id; 
 } 
 
 public int getFlightId() { 
 return flightId; 
 } 
 
 public List<Passenger> getPassengers() {
 return passengers; 
 } 
 
 @Override 
 public String toString() { 
 return "Booking #" + id + " for Flight #" + flightId + " | Passengers: " + passengers; 
 } 
}