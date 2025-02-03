/**
 * Represents a simple task with no specific date or time constraints.
 * Inherits from the {@link Task} class.
 */

package tasktypes;

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
}
