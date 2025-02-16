/**
 * Command to add a new ToDo task to the task list.
 * This command parses the user input to create a ToDo task and adds it to the list.
 */

package amadeus.cognition;

import amadeus.brain.AmadeusException;
import amadeus.workspace.TaskList;
import amadeus.workspace.ToDo;

public class ToDoCommand extends Command
{
    ToDo td;

    /**
     * Constructs a new `ToDoCommand` by parsing the user input.
     * The input is expected to contain a task description.
     *
     * @param input The user input containing the task description.
     * @throws AmadeusException If the input is empty.
     */
    public ToDoCommand(String input) throws AmadeusException
    {
        // Check if the input is empty
        if (input.isEmpty()) {
            // Missing Argument in the Input Exception
            throw AmadeusException.missingArgument("TODO");
        }

        // Create a new ToDo Task
        td = new ToDo(input);
    }

    /**
     * Executes the command by adding the ToDo task to the task list.
     *
     * @throws AmadeusException If an error occurs while adding the task.
     */
    @Override
    public void execute() throws AmadeusException
    {
        TaskList.addToDo(td);
    }
}
