/**
 * Command to mark a task as complete or incomplete.
 * This command parses the user input to identify the task index and updates its status.
 */

package amadeus.cognition;

import amadeus.brain.AmadeusException;
import amadeus.perception.NumberConverter;
import amadeus.workspace.TaskList;

public class MarkCommand extends Command
{
    private final int index;
    private final boolean isDone;

    /**
     * Constructs a new `MarkCommand` by parsing the user input.
     * The input is expected to contain a valid task index.
     *
     * @param input The user input containing the task index.
     * @param mark  Indicates whether the task should be marked as complete (`true`) or incomplete (`false`).
     * @throws AmadeusException If the input is empty or the index is invalid.
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
     * @throws AmadeusException If an error occurs while updating the task status.
     */
    @Override
    public void execute() throws AmadeusException
    {
        TaskList.markDone(index, isDone);
    }
}
