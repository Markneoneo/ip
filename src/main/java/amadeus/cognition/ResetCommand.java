package amadeus.cognition;

import amadeus.brain.AmadeusException;
import amadeus.workspace.TaskList;

/**
 * Command to reset the task list.
 * <p>
 * This command clears all tasks from the task list and updates the database.
 * </p>
 */
public class ResetCommand extends Command
{
    /**
     * Resets the task list by clearing all tasks and updating the database.
     * <p>
     * This method calls the {@link TaskList#resetList()} method to perform the reset operation.
     * </p>
     *
     * @throws AmadeusException if an error occurs while resetting the task list.
     */
    @Override
    public void execute() throws AmadeusException
    {
        TaskList.resetList();
    }
}
