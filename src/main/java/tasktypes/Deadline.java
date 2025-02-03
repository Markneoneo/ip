/**
 * Represents a task with a specific deadline.
 * Inherits from the {@link Task} class.
 */

package tasktypes;

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
}
