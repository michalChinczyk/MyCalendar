package me.michal.projects.MyCalendar;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Main application class for the simple calendar app.
 * This class is responsible for managing user input, scheduling events,
 * displaying events, and serializing/deserializing event data.
 */
public final class App {
    /**
     * Private constructor to prevent instantiation of the App class.
     * This is a utility class, so it should not be instantiated.
     * Throws an InstantiationError if called.
     */
    private App() throws InstantiationError {
        throw new InstantiationError();
    }

    /**
     * The main method is the entry point of the calendar application.
     * It handles the main logic of the app: scheduling new events, displaying
     * events,
     * and saving/loading event data from a file.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        final Calendar myCalendar = new Calendar();
        Serializer.deserializeData("eventData.edt", myCalendar);

        final Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. schedule new event \n2.display events\n3. quit");
            final int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    final LocalDateTime start = Utils.readDateTimeFromConsole(scanner);
                    final LocalDateTime end = Utils.readDateTimeFromConsole(scanner);
                    System.out.println("enter name of the event");
                    final String name = scanner.nextLine();
                    try {
                        myCalendar.book(start, end, name);
                        System.out.println("event succesfully scheduled");
                    } catch (IllegalArgumentException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 2:
                    myCalendar.displayEvents();
                    break;
                case 3:
                    Serializer.serializeData("eventData.edt", myCalendar);
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.err.println("unknown command.");
            }
        }
    }
}
