package amadeus.workspace;

import amadeus.perception.DateConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a scheduled event task with a start and end time.
 * <p>
 * This class extends the {@link Task} class and provides functionality specific to event tasks.
 * </p>
 */
public class Event extends Task {
    /**
     * The start date/time of the event.
     */
    protected Object from;

    /**
     * The end date/time of the event.
     */
    protected Object to;


    /**
     * Constructs an {@code Event} task with a name, start time, and end time.
     *
     * @param name the name or description of the event; must not be {@code null}.
     * @param from the start date/time of the event; must not be {@code null}.
     * @param to   the end date/time of the event; must not be {@code null}.
     */
    public Event(String name, Object from, Object to) {
        super(name);
        this.from = from;
        this.to = to;
    }


    /**
     * Constructs an {@code Event} task with a name, completion status, start time, and end time.
     *
     * @param name the name or description of the event; must not be {@code null}.
     * @param done a boolean indicating whether the task is completed (true) or not (false).
     * @param from the start date/time of the event; must not be {@code null}.
     * @param to   the end date/time of the event; must not be {@code null}.
     */
    public Event(String name, boolean done, Object from, Object to) {
        super(name, done);
        this.from = from;
        this.to = to;
    }


    /**
     * Returns the start date/time of the event.
     *
     * @return The start date/time of the event.
     */
    public Object getFrom() {
        return from;
    }


    /**
     * Returns the details of the {@code Event} task, including the start and end times.
     *
     * @return the details of the event as a formatted string.
     */
    @Override
    public String getDetails() {
        return "\uD83D\uDE80 \033[31;1mFrom:\033[0m【" + DateConverter.formatDate(from) +
                "】 \uD83D\uDEA9 \033[31;1mTo:\033[0m【" + DateConverter.formatDate(to) + "】";
    }


    /**
     * Returns a string representation of the {@code Event}, including its name, start time, and end time.
     *
     * @return a formatted string representing the event details.
     */
    @Override
    public String toString() {
        return name + " \uD83D\uDE80 \033[31;1mFrom:\033[0m【" + DateConverter.formatDate(from) +
                "】 \uD83D\uDEA9 \033[31;1mTo:\033[0m【" + DateConverter.formatDate(to) + "】";
    }


    /**
     * Converts the {@code Event} task to a file-friendly format.
     * <p>
     * The format is: {@code E | <isDone> | <description> | <from> | <to>}.
     * </p>
     *
     * @return a string representation of the {@code Event} task in a format suitable for saving to a file.
     */
    @Override
    public String toFileFormat() {
        String fromFormatted = dateFileFormat(from);
        String toFormatted = dateFileFormat(to);
        return "E | " + (isDone ? "1" : "0") + " | " + name + " | " + fromFormatted + " | " + toFormatted;
    }


    /**
     * Formats a date or date-time object into a string for saving to a file.
     *
     * @param date the date or date-time object to format; must not be {@code null}.
     * @return the formatted date string.
     */
    private String dateFileFormat(Object date) {
        if (date instanceof LocalDateTime) {
            return ((LocalDateTime) date).format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));

        } else if (date instanceof LocalDate) {
            return ((LocalDate) date).format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        } else {

            return "Invalid Date";
        }
    }
}
