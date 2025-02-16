/**
 * Represents a task with a specific deadline.
 * Inherits from the {@link Task} class.
 */

package amadeus.workspace;

public class Deadline extends Task
{
    // The due date/time of the deadline task.
    protected String by;

    /**
     * Constructs a Deadline task with a name and due date/time.
     *
     * @param name The name or description of the task.
     * @param by   The deadline by which the task must be completed.
     */
    public Deadline(String name, String by)
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
    public Deadline(String name, boolean done, String by)
    {
        super(name, done);
        this.by = by;
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
        return name + " ⏰Due by:【" + by + "】";
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
        return "D | " + (isDone ? "1" : "0") + " | " + name + " | " + by;
    }
}
