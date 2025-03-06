package amadeus.workspace;

/**
 * Represents a simple task with no specific date or time constraints.
 * <p>
 * This class extends the {@link Task} class and provides functionality specific to ToDo tasks.
 * </p>
 */
public class ToDo extends Task {
    /**
     * Constructs a {@code ToDo} task with a given name or description.
     *
     * @param name the name or description of the task; must not be {@code null}.
     */
    public ToDo(String name) {
        super(name);
    }


    /**
     * Constructs a {@code ToDo} task with a given name and completion status.
     *
     * @param name the name or description of the task; must not be {@code null}.
     * @param done a boolean indicating whether the task is completed (true) or not (false).
     */
    public ToDo(String name, boolean done) {
        super(name, done);
    }


    /**
     * Converts the {@code ToDo} task to a file-friendly format.
     * <p>
     * The format is: {@code T | <isDone> | <description>}.
     * </p>
     *
     * @return a string representation of the {@code ToDo} task in a format suitable for saving to a file.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + name;
    }


    /**
     * Returns the details of the {@code ToDo} task.
     * <p>
     * Since {@code ToDo} tasks have no additional details, this method returns an empty string.
     * </p>
     *
     * @return an empty string.
     */
    @Override
    public String getDetails() {
        return ""; // No details for ToDo tasks
    }
}
