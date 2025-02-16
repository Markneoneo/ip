/**
 * Command to display the current list of tasks.
 * This command prints the list of tasks to the console, organized by type.
 */

package amadeus.cognition;
import amadeus.personality.Speech;
import static amadeus.workspace.TaskList.taskList;

public class ListCommand extends Command
{
    /**
     * Displays the current list of tasks to the console.
     * The tasks are organized by type (Deadlines, Events, ToDos).
     */
    @Override
    public void execute()
    {
        Speech.sayList(taskList);
    }
}