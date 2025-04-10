package org.example.service;
  
import org.example.dao.BookingDAO;
import org.example.model.Booking;
import org.example.model.Flight;

import java.util.List;
 
public class BookingService { 
 private final BookingDAO bookingDAO;
 private final FlightService flightService; 
 
 public BookingService(BookingDAO bookingDAO, FlightService flightService) { 
 this.bookingDAO = bookingDAO; 
 this.flightService = flightService; 
 } 
 
 public Booking getBookingById(int id) {
 return bookingDAO.getBookingById(id); 
 } 
 
 public List getBookingsByPassengerName(String fullName) { 
 return bookingDAO.getBookingsByPassengerName(fullName); 
 } 
 
 public void createBooking(Flight flight, List passengers) {
 int bookingId = bookingDAO.generateNextId(); 
 Booking booking = new Booking(bookingId, flight.getId(), passengers); 
 bookingDAO.addBooking(booking); 
 flightService.decreaseSeats(flight, passengers.size()); 
 } 
 
 public boolean cancelBooking(int bookingId) { 
 Booking booking = bookingDAO.getBookingById(bookingId); 
 if (booking != null) { 
 Flight flight = flightService.getFlightById(booking.getFlightId()); 
 if (flight != null) {
 flight.setAvailableSeats(flight.getAvailableSeats() + booking.getPassengers().size()); 
 bookingDAO.removeBooking(booking); 
 return true; 
 } 
 } 
 return false; 
 } 
}







