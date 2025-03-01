package amadeus.workspace;

/**
 * Abstract base class representing a generic task.
 * <p>
 * This class provides the foundational properties and methods for tasks, including
 * a name, completion status, and methods to update and display task details.
 * Subclasses can extend this class to create specific task types (e.g., {@link Deadline}, {@link Event}, {@link ToDo}).
 * </p>
 */
public abstract class Task
{
    /**
     * The name or description of the task.
     */
    protected String name;
    /**
     * The completion status of the task (true if complete, false if incomplete).
     */
    protected boolean isDone;


    /**
     * Initializes a new {@code Task} with the given name.
     * The task is marked as incomplete by default.
     *
     * @param input the name or description of the task; must not be {@code null}.
     */
    public Task(String input)
    {
        this.name = input;
        this.isDone = false;
    }


    /**
     * Initializes a new {@code Task} with the given name and completion status.
     *
     * @param input the name or description of the task; must not be {@code null}.
     * @param done  a boolean indicating whether the task is completed (true) or not (false).
     */
    public Task(String input, boolean done)
    {
        this.name = input;
        this.isDone = done;
    }


    /**
     * Returns the name or description of the task.
     *
     * @return the name or description of the task.
     */
    public String getName()
    {
        return name;
    }


    /**
     * Returns the completion status of the task.
     *
     * @return {@code true} if the task is complete, {@code false} otherwise.
     */
    public boolean getDone() { return isDone; }


    /**
     * Returns the details of the task.
     * <p>
     * Subclasses must implement this method to provide task-specific details.
     * </p>
     *
     * @return the details of the task as a formatted string.
     */
    public abstract String getDetails(); // To be implemented by subclasses


    /**
     * Updates the completion status of the task.
     *
     * @param status the new completion status (true for complete, false for incomplete).
     */
    public void updateDone(boolean status)
    {
        this.isDone = status;
    }


    /**
     * Returns a string representation of the task, which is its name.
     *
     * @return The name or description of the task as a String.
     */
    public String toString()
    {
        return this.name;
    }


    /**
     * Prints the task's name and completion status to the console.
     * If the task is complete, a checkmark (✔️) is displayed next to the name.
     */
    public void printTask()
    {
        System.out.print(this + (isDone ? " ✔️" : "") + "\n");
    }


    /**
     * Converts the task to a file-friendly format.
     * <p>
     * Subclasses must implement this method to provide a format suitable for saving to a file.
     * </p>
     *
     * @return a string representation of the task in a format suitable for saving to a file.
     */
    public abstract String toFileFormat();
}
