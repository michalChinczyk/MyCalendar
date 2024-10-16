package me.michal.projects.MyCalendar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Utils {
  public static int encodeDateTime(final LocalDateTime dateTime) {
  // Encode date and time as YYYYMMDDHHMM
  return dateTime.getYear() * 100000000 + 
    dateTime.getMonthValue() * 1000000 + 
    dateTime.getDayOfMonth() * 10000 + 
    dateTime.getHour() * 100 + 
    dateTime.getMinute();
  }
	
  public static String decodeDateTime(final int encodedTime) {
    final int year = encodedTime / 100000000;
    final int month = (encodedTime / 1000000) % 100;
    final int day = (encodedTime / 10000) % 100;
    final int hour = (encodedTime / 100) % 100;
    final int minute = encodedTime % 100;

    return String.format("%04d-%02d-%02d %02d:%02d", year, month, day, hour, minute);
  }
	
  public static LocalDateTime readDateTimeFromConsole(Scanner scanner) {
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); // Expected date format
        
    LocalDateTime dateTime = null;
        
    while (dateTime == null) {
      System.out.println("Please enter the date and time in the format 'yyyy-MM-dd HH:mm': ");
      final String input = scanner.nextLine(); // Read the line from console
            
      try {
        // Parse the input string to LocalDateTime
        dateTime = LocalDateTime.parse(input, formatter);
        System.out.println("Successfully parsed: " + dateTime);
      } catch (DateTimeParseException e) {
        // If parsing fails, print an error message and continue the loop
        System.out.println("Invalid format. Please try again.");
      }
    }
        
    return dateTime;
  }
}
