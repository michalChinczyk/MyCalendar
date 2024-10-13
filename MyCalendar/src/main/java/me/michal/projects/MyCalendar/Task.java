package me.michal.projects.MyCalendar;

import java.io.Serializable;
import java.time.*;

/**
 * The Task class represents a scheduled event or task in the calendar.
 * It implements Serializable to allow tasks to be serialized and deserialized.
 */
public class Task implements Serializable{
	private String name;
	private LocalDateTime start;
	private LocalDateTime finish;
	
	public String getName() {return name;}
	public LocalDateTime getStart() {return start;}
	public LocalDateTime getFinish() {return finish;}
	
	 /**
     * Constructor to create a new Task object.
     *
     * @param name   The name of the task/event.
     * @param start  The start date and time of the task.
     * @param finish The end date and time of the task.
     */
	public Task(String name, LocalDateTime start, LocalDateTime finish) {
		this.name = name;
		this.start = start;
		this.finish = finish;
	}
	
	/**
     * Displays the task details, including its name, start time, and end time.
     * Outputs the details in the format:
     * "TaskName start: yyyy-MM-ddTHH:mm:ss; end: yyyy-MM-ddTHH:mm:ss"
     */
	public void display() {
		System.out.println(name + " start: " +  start.toString() + "; end: " + finish.toString());
	}
}
