package amadeus.cognition;

import amadeus.brain.AmadeusException;
import amadeus.workspace.TaskList;
import amadeus.workspace.ToDo;

/**
 * Command to add a new {@link amadeus.workspace.ToDo} task to the task list.
 * <p>
 * This command parses the user input to create a {@link amadeus.workspace.ToDo} task and adds it to the list.
 * The input is expected to contain a task description.
 * </p>
 */
public class ToDoCommand extends Command {
    ToDo td;

    /**
     * Constructs a new {@code ToDoCommand} by parsing the user input.
     * <p>
     * The input is expected to contain a task description. If the input is empty,
     * an {@link amadeus.brain.AmadeusException} is thrown.
     * </p>
     *
     * @param input the user input containing the task description; must not be {@code null} or empty.
     * @throws AmadeusException if the input is empty or invalid.
     */
    public ToDoCommand(String input) throws AmadeusException {
        // Check if the input is empty
        if (input.isEmpty()) {
            // Missing Argument in the Input Exception
            throw AmadeusException.missingArgument("TODO");
        }

        // Create a new ToDo Task
        td = new ToDo(input);
    }

    /**
     * Executes the command by adding the {@link amadeus.workspace.ToDo} task to the task list.
     *
     * @throws AmadeusException if an error occurs while adding the task.
     */
    @Override
    public void execute() throws AmadeusException {
        TaskList.addToDo(td);
    }
}
