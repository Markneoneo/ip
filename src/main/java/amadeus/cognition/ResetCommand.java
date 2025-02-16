/**
 * Command to reset the task list.
 * This command clears all tasks from the task list and updates the database.
 */

package amadeus.cognition;
import amadeus.brain.AmadeusException;
import amadeus.workspace.TaskList;

public class ResetCommand extends Command
{
    /**
     * Resets the task list by clearing all tasks and updating the database.
     *
     * @throws AmadeusException If an error occurs while resetting the task list.
     */
    @Override
    public void execute() throws AmadeusException
    {
        TaskList.resetList();
    }
}
