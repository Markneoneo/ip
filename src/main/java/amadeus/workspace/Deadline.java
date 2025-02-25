/**
 * Represents a task with a specific deadline.
 * Inherits from the {@link Task} class.
 */

package amadeus.workspace;
import amadeus.perception.DateConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task
{
    // The due date/time of the deadline task.
    protected Object by;


    /**
     * Constructs a Deadline task with a name and due date/time.
     *
     * @param name The name or description of the task.
     * @param by   The deadline by which the task must be completed.
     */
    public Deadline(String name, Object by)
    {
        super(name);
        this.by = by;
    }


    /**
     * Constructs a Deadline task with a name, completion status and due date/time.
     *
     * @param name The name or description of the task.
     * @param done  A boolean indicating whether the task is completed (true) or not (false).
     * @param by   The deadline by which the task must be completed.
     */
    public Deadline(String name, boolean done, Object by)
    {
        super(name, done);
        this.by = by;
    }


    /**
     * Returns the due date/time of the deadline task.
     *
     * @return The due date/time of the deadline task.
     */
    public Object getBy()
    {
        return by;
    }


    /**
     * Returns a string representation of the Deadline, including its name
     * and due date/time.
     *
     * @return A formatted string representing the deadline details.
     */
    @Override
    public String toString()
    {
        return name + " ⏰Due by:【" + DateConverter.formatDate(by) + "】";
    }


    /**
     * Converts the Deadline task to a file-friendly format.
     * The format is: `D | <isDone> | <description> | <by>`
     *
     * @return A string representation of the Deadline task in a format suitable for saving to a file.
     */
    @Override
    public String toFileFormat()
    {
        String byFormatted = dateFileFormat(by);
        return "D | " + (isDone ? "1" : "0") + " | " + name + " | " + byFormatted;
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
