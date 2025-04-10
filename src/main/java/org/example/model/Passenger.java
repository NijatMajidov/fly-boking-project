package org.example.model;
 
public class Passenger { 
 private String firstName; 
 private String lastName; 
 
 public Passenger(String firstName, String lastName) { 
 this.firstName = firstName; 
 this.lastName = lastName; 
 }

 public String getFullName() {
 return firstName + " " + lastName; 
 } 
 
 @Override 
 public String toString() { 
 return getFullName(); 
 } 
}