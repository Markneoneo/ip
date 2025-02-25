/**
 * Represents a scheduled event task with a start and end time.
 * Inherits from the {@link Task} class.
 */

package amadeus.workspace;
import amadeus.perception.DateConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task
{
    // The start date/time of the event.
    protected Object from;
    // The end date/time of the event.
    protected Object to;


    /**
     * Constructs an Event task with a name, start time, and end time.
     *
     * @param name The name or description of the event.
     * @param from The start date/time of the event.
     * @param to   The end date/time of the event.
     */
    public Event(String name, Object from, Object to)
    {
        super(name);
        this.from = from;
        this.to = to;
    }


    /**
     * Constructs an Event task with a name, completion status, start time, and end time.
     *
     * @param name The name or description of the event.
     * @param done  A boolean indicating whether the task is completed (true) or not (false).
     * @param from The start date/time of the event.
     * @param to   The end date/time of the event.
     */
    public Event(String name, boolean done, Object from, Object to)
    {
        super(name, done);
        this.from = from;
        this.to = to;
    }


    /**
     * Returns the start date/time of the event.
     *
     * @return The start date/time of the event.
     */
    public Object getFrom()
    {
        return from;
    }


    /**
     * Returns a string representation of the Event, including its name,
     * start time, and end time.
     *
     * @return A formatted string representing the event details.
     */
    @Override
    public String toString()
    {
        return name + " \uD83D\uDE80From:【" + DateConverter.formatDate(from) + "】 \uD83D\uDEA9To:【" + DateConverter.formatDate(to) + "】";
    }


    /**
     * Converts the Event task to a file-friendly format.
     * The format is: `E | <isDone> | <description> | <from> | <to>`
     *
     * @return A string representation of the Event task in a format suitable for saving to a file.
     */
    @Override
    public String toFileFormat()
    {
        String fromFormatted = dateFileFormat(from);
        String toFormatted = dateFileFormat(to);
        return "E | " + (isDone ? "1" : "0") + " | " + name + " | " + fromFormatted + " | " + toFormatted;
    }


    /**
     * Formats a date or date-time object into a string for saving to a file.
     *
     * @param date The date or date-time object to format.
     * @return The formatted date string.
     */
    private String dateFileFormat(Object date)
    {
        if (date instanceof LocalDateTime) {
            return ((LocalDateTime) date).format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));

        } else if (date instanceof LocalDate) {
            return ((LocalDate) date).format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        } else {

            return "Invalid Date";
        }
    }
}
