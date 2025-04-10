package org.example.dao;
 
import org.example.model.Booking;
import org.example.model.Passenger;
import org.example.util.FileUtil;

import java.util.ArrayList;
import java.util.Arrays; 
import java.util.List; 
import java.util.stream.Collectors; 
 
public class BookingDAO { 
 private List<Booking> bookings = new ArrayList<>();
 private final String bookingFile = "data/bookings.txt"; 
 
 public BookingDAO() { 
 loadBookings(); 
 } 
 
 private void loadBookings() { 
 List<String> lines = FileUtil.readFile(bookingFile);
 for (String line : lines) { 
 String[] parts = line.split(";"); 
 int id = Integer.parseInt(parts[0]); 
 int flightId = Integer.parseInt(parts[1]); 
 List passengers = Arrays.stream(parts[2].split(",")) 
 .map(fullName -> { 
 String[] nameParts = fullName.trim().split(" "); 
 return new Passenger(nameParts[0], nameParts[1]);
 }) 
 .collect(Collectors.toList()); 
 bookings.add(new Booking(id, flightId, passengers));
 } 
 } 
 
 public void saveBookings() { 
 List lines = new ArrayList<>(); 
 for (Booking booking : bookings) { 
 String passengersStr = booking.getPassengers().stream() 
 .map(Passenger::getFullName) 
 .collect(Collectors.joining(",")); 
 lines.add(booking.getId() + ";" + booking.getFlightId() + ";" + passengersStr); 
 } 
 FileUtil.writeFile(bookingFile, lines); 
 } 
 
 public List getAllBookings() { 
 return bookings; 
 } 
 
 public Booking getBookingById(int id) { 
 return bookings.stream() 
 .filter(b -> b.getId() == id) 
 .findFirst() 
 .orElse(null); 
 }

 public List<Booking> getBookingsByPassengerName(String fullName) {
  return bookings.stream()
          .filter(b -> b.getPassengers().stream()
                  .anyMatch(p -> p.getFullName().equalsIgnoreCase(fullName)))
          .toList();
 }

 public void addBooking(Booking booking) { 
 bookings.add(booking); 
 saveBookings(); 
 } 
 
 public void removeBooking(Booking booking) { 
 bookings.remove(booking); 
 saveBookings(); 
 } 
 
 public int generateNextId() { 
 return bookings.stream().mapToInt(Booking::getId).max().orElse(0) + 1; 
 } 
}