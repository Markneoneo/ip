/**
 * Represents a simple task with no specific date or time constraints.
 * Inherits from the {@link Task} class.
 */

package amadeus.workspace;

public class ToDo extends Task
{
    /**
     * Constructs a ToDo task with a given name or description.
     *
     * @param name The name or description of the task.
     */
    public ToDo(String name)
    {
        super(name);
    }

    /**
     * Constructs a ToDo task with a given name and completion status.
     *
     * @param name  The name or description of the task.
     * @param done  A boolean indicating whether the task is completed (true) or not (false).
     */
    public ToDo(String name, boolean done) { super(name, done); }

    /**
     * Converts the ToDo task to a file-friendly format.
     * The format is: `T | <isDone> | <description>`
     *
     * @return A string representation of the ToDo task in a format suitable for saving to a file.
     */
    @Override
    public String toFileFormat()
    {
        return "T | " + (isDone ? "1" : "0") + " | " + name;
    }
}
