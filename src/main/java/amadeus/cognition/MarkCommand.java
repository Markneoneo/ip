package amadeus.cognition;

import amadeus.brain.AmadeusException;
import amadeus.perception.NumberConverter;
import amadeus.workspace.TaskList;

/**
 * Command to mark a task as complete or incomplete.
 * <p>
 * This command parses the user input to identify the task index and updates its status.
 * The input is expected to contain a valid task index.
 * </p>
 */
public class MarkCommand extends Command
{
    private final int index;
    private final boolean isDone;

    /**
     * Constructs a new {@code MarkCommand} by parsing the user input.
     * <p>
     * The input is expected to contain a valid task index. If the input is empty or the index is invalid,
     * an {@link amadeus.brain.AmadeusException} is thrown.
     * </p>
     *
     * @param input the user input containing the task index; must not be {@code null} or empty.
     * @param mark  indicates whether the task should be marked as complete ({@code true}) or incomplete ({@code false}).
     * @throws AmadeusException if the input is empty or the index is invalid.
     */
    public MarkCommand(String input, boolean mark) throws AmadeusException
    {
        this.isDone = mark;

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
     * Executes the command by marking the task at the specified index as complete or incomplete.
     *
     * @throws AmadeusException if an error occurs while updating the task status.
     */
    @Override
    public void execute() throws AmadeusException
    {
        TaskList.markDone(index, isDone);
    }
}
