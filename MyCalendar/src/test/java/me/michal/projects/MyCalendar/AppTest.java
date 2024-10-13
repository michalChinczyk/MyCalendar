package me.michal.projects.MyCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	private Calendar myCalendar;
	
	@BeforeEach
	void setUp() {
		myCalendar = new Calendar();
	}

    @Test
    void testBookingSuccess() {
        LocalDateTime start = LocalDateTime.of(2024, 10, 1, 10, 0);
        LocalDateTime end = LocalDateTime.of(2024, 10, 1, 12, 0);
        String eventName = "Test Event";

        // Attempt to book the event
        myCalendar.book(start, end, eventName);

        // Assert that the event was successfully booked
        assertTrue(myCalendar.hasEvent(start, end));
    }

    @Test
    void testBookingOverlapFailure() {
        LocalDateTime start1 = LocalDateTime.of(2024, 10, 1, 10, 0);
        LocalDateTime end1 = LocalDateTime.of(2024, 10, 1, 12, 0);
        LocalDateTime start2 = LocalDateTime.of(2024, 10, 1, 11, 0); // overlapping time
        LocalDateTime end2 = LocalDateTime.of(2024, 10, 1, 13, 0);

        // Book first event
        myCalendar.book(start1, end1, "First Event");

        // Try booking second overlapping event and expect an exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            myCalendar.book(start2, end2, "Second Event");
        });

        assertEquals("you already have an event scheduled at this time!", exception.getMessage());
    }
}
