/**
 * Abstract base class representing a generic task.
 * This class provides the foundational properties and methods for tasks, including
 * a name, completion status, and methods to update and display task details.
 * Subclasses can extend this class to create specific task types (e.g., Deadline, Event, ToDo, etc.).
 */

package amadeus.task;

public class Task
{
    // The name or description of the task.
    protected String name;
    // The completion status of the task (true if complete, false if incomplete).
    protected boolean isDone;

    /**
     * Initializes a new Task with the given name.
     * The task is marked as incomplete by default.
     *
     * @param input The name or description of the task.
     */
    public Task(String input)
    {
        this.name = input;
        this.isDone = false;
    }

    /**
     * Initializes a new Task with the given name and completion status.
     *
     * @param input The name or description of the task.
     * @param done  A boolean indicating whether the task is completed (true) or not (false).
     */
    public Task(String input, boolean done)
    {
        this.name = input;
        this.isDone = done;
    }

    /**
     * Updates the name or description of the task.
     *
     * @param input The new name or description for the task.
     */
    public void setName(String input)
    {
        this.name = input;
    }

    /**
     * Retrieves the name or description of the task.
     *
     * @return The name or description of the task as a String.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Checks whether the task is marked as complete.
     *
     * @return True if the task is complete, false if it is incomplete.
     */
    public boolean getDone()
    {
        return isDone;
    }

    /**
     * Updates the completion status of the task.
     *
     * @param status The new completion status (true for complete, false for incomplete).
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
     * Converts the Misc. Task to a file-friendly format.
     * The format is: `M | <isDone> | <description>`
     *
     * @return A string representation of the generic task in a format suitable for saving to a file.
     */
    public String toFileFormat()
    {
        return "M | " + (isDone ? "1" : "0") + " | " + name;
    }

    /**
     * Prints the task's name and completion status to the console.
     * If the task is complete, a checkmark (✔️) is displayed next to the name.
     */
    public void printTask()
    {
        System.out.print(this + (isDone ? " ✔️" : "") + "\n");
    }
}
