/**
 * Command to add a new Deadline task to the task list.
 * This command parses the user input to create a Deadline task and adds it to the list.
 */

package amadeus.cognition;

import amadeus.brain.AmadeusException;
import amadeus.workspace.Deadline;
import amadeus.workspace.TaskList;

public class DeadlineCommand extends Command
{
    Deadline d;

    /**
     * Constructs a new `DeadlineCommand` by parsing the user input.
     * The input is expected to contain a description and a due date, separated by "/by".
     *
     * @param input The user input containing the task description and due date.
     * @throws AmadeusException If the input is empty or in an invalid format.
     */
    public DeadlineCommand(String input) throws AmadeusException
    {
        // Check if the input is empty
        if (input.isEmpty()) {
            // Missing Argument in the Input Exception
            throw AmadeusException.missingArgument("DEADLINE");
        }

        // Split the input into description and due date
        String[] parts = input.split(" /by ", 2);

        // Check if the input is in the correct format
        if (parts.length != 2) // Missing Due Date
        {
            // Invalid Deadline Format Exception
            throw AmadeusException.invalidDeadline();
        }

        // Create a new Deadline task
        String name = parts[0].trim();
        String by = parts[1].trim();
        d = new Deadline(name, by);
    }

    /**
     * Executes the command by adding the Deadline task to the task list.
     *
     * @throws AmadeusException If an error occurs while adding the task.
     */
    @Override
    public void execute() throws AmadeusException
    {
        TaskList.addDeadline(d);
    }
}
