package org.example.controller;
 
 
import org.example.model.Booking;
import org.example.model.Flight;
import org.example.service.BookingService;

import java.util.List;
 
public class BookingController { 
 private final BookingService bookingService;
 
 public BookingController(BookingService bookingService) { 
 this.bookingService = bookingService; 
 } 
 
 public Booking getBookingById(int id) {
 return bookingService.getBookingById(id); 
 } 
 
 public List getBookingsByPassenger(String fullName) { 
 return bookingService.getBookingsByPassengerName(fullName); 
 } 
 
 public void createBooking(Flight flight, List passengers) {
 bookingService.createBooking(flight, passengers); 
 } 
 
 public boolean cancelBooking(int bookingId) { 
 return bookingService.cancelBooking(bookingId); 
 } 
}







