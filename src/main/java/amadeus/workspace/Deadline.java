package amadeus.workspace;

import amadeus.perception.DateConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * <p>
 * This class extends the {@link Task} class and provides functionality specific to deadline tasks.
 * </p>
 */
public class Deadline extends Task
{
    /**
     * The due date/time of the deadline task.
     */
    protected Object by;


    /**
     * Constructs a {@code Deadline} task with a name and due date/time.
     *
     * @param name the name or description of the task; must not be {@code null}.
     * @param by   the deadline by which the task must be completed; must not be {@code null}.
     */
    public Deadline(String name, Object by)
    {
        super(name);
        this.by = by;
    }


    /**
     * Constructs a {@code Deadline} task with a name, completion status, and due date/time.
     *
     * @param name the name or description of the task; must not be {@code null}.
     * @param done a boolean indicating whether the task is completed (true) or not (false).
     * @param by   the deadline by which the task must be completed; must not be {@code null}.
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
     * Returns the details of the {@code Deadline} task, including the due date/time.
     *
     * @return the details of the deadline as a formatted string.
     */
    @Override
    public String getDetails()
    {
        return " ⏰ \033[31;1mDue by:\033[0m【" + DateConverter.formatDate(by) + "】";
    }


    /**
     * Returns a string representation of the {@code Deadline}, including its name and due date/time.
     *
     * @return a formatted string representing the deadline details.
     */
    @Override
    public String toString()
    {
        return name + " ⏰ \033[31;1mDue by:\033[0m【" + DateConverter.formatDate(by) + "】";
    }


    /**
     * Converts the {@code Deadline} task to a file-friendly format.
     * <p>
     * The format is: {@code D | <isDone> | <description> | <by>}.
     * </p>
     *
     * @return a string representation of the {@code Deadline} task in a format suitable for saving to a file.
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
     * @param date the date or date-time object to format; must not be {@code null}.
     * @return the formatted date string.
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
