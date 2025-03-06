package amadeus.cognition;

import amadeus.brain.AmadeusException;
import amadeus.perception.NumberConverter;
import amadeus.workspace.TaskList;

/**
 * Command to delete a task from the task list.
 * <p>
 * This command parses the user input to identify the task index and removes the task.
 * The input is expected to contain a valid task index.
 * </p>
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a new {@code DeleteCommand} by parsing the user input.
     * <p>
     * The input is expected to contain a valid task index. If the input is empty or the index is invalid,
     * an {@link amadeus.brain.AmadeusException} is thrown.
     * </p>
     *
     * @param input the user input containing the task index; must not be {@code null} or empty.
     * @throws AmadeusException if the input is empty or the index is invalid.
     */
    public DeleteCommand(String input) throws AmadeusException {
        // Check if the input is empty
        if (input.isEmpty()) {
            // Missing Number in the Input Exception
            throw AmadeusException.missingNumber();
        }

        // Extract the task index from the input
        this.index = NumberConverter.extractIndex(input);

        // Check if the index is valid
        if (index == -1) {
            // Invalid Number in the Input Exception
            throw AmadeusException.invalidNumber();
        }
    }

    /**
     * Executes the command by deleting the task at the specified index from the task list.
     *
     * @throws AmadeusException if an error occurs while deleting the task.
     */
    @Override
    public void execute() throws AmadeusException {
        TaskList.deleteTask(index);
    }
}
