package me.michal.projects.MyCalendar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Calendar class manages scheduled events and tasks.
 * It allows booking events, checking availability, and displaying the events.
 * Events are stored in a TreeMap for efficient retrieval and checking.
 */
public class Calendar {
  private final TreeMap<Integer, Integer> events;
  private final List<Task> eventData;
  
  public List<Task> getEventData() {
    return eventData;
  }
	
	 /**
     * Constructor to create a new Calendar object.
     * Initializes an empty TreeMap for events and an empty list for event data.
     */
  public Calendar() {
    events = new TreeMap<>();
    eventData = new ArrayList<>();
  }
	
	/**
     * Books a new event in the calendar.
     * Adds the event to the TreeMap and the list of event data if the time slot is available.
     * 
     * @param start  The start date and time of the event.
     * @param finish The end date and time of the event.
     * @param name   The name of the event.
     * @throws IllegalArgumentException If the time slot is not available.
     */
  public void book(final LocalDateTime start, final LocalDateTime finish, final String name) {
    if (!checkIfAvailable(start, finish)) {
      throw new IllegalArgumentException("you already have an event scheduled at this time!");
    }
    events.put(Utils.encodeDateTime(start), Utils.encodeDateTime(finish));
    eventData.add(new Task(name, start, finish));
  }
	
  /**
   * Checks if a given time slot is available for booking.
   * Ensures that there is no overlap with existing events.
   * 
   * @param start The proposed start date and time of the event.
   * @param end   The proposed end date and time of the event.
   * @return true if the time slot is available; false otherwise.
   */
  public boolean checkIfAvailable(final LocalDateTime start, final LocalDateTime end) {
    final int startEncoded = Utils.encodeDateTime(start);
    final int endEncoded = Utils.encodeDateTime(end);

    // Ensure the start time is before the end time
    if (startEncoded >= endEncoded) {
      return false;
    }

    // Check if there is a conflict with existing events
    final Map.Entry<Integer, Integer> lower = events.floorEntry(startEncoded); 
    final Map.Entry<Integer, Integer> higher = events.ceilingEntry(startEncoded); 

    // If lower event exists, ensure it doesn't overlap
    if  (lower != null && lower.getValue() > startEncoded) {
      return false;
    }

    // If higher event exists, ensure it doesn't overlap
    return higher == null || higher.getKey() >= endEncoded;
  
  }
	
	/**
     * Checks if a specific event exists within a given time range.
     * 
     * @param startDate The start date and time of the event.
     * @param endDate   The end date and time of the event.
     * @return true if an event exists in the given time range; false otherwise.
     */
  public boolean hasEvent(final LocalDateTime startDate, final LocalDateTime endDate) {
    final int start = Utils.encodeDateTime(startDate);
    final int end = Utils.encodeDateTime(endDate);
    return events.containsKey(start) && events.get(start) == end;
  }
	
	/**
     * Displays all scheduled events by calling the display method of each Task.
     */
  public void displayEvents() {
    for (final Task task : eventData) {
      task.display();
    }
  }
}
