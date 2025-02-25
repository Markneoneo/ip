package amadeus.cognition;

import amadeus.brain.AmadeusException;
import amadeus.personality.Speech;
import amadeus.workspace.Task;
import amadeus.workspace.TaskList;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command
{
    private final String keyword; // The keyword to search for
    private final ArrayList<Task> matchingTasks; // List of tasks matching the keyword

    /**
     * Constructs a FindCommand by parsing the user input for the keyword.
     *
     * @param argument The user input containing the keyword to search for.
     * @throws AmadeusException If the input is empty or invalid.
     */
    public FindCommand(String argument) throws AmadeusException
    {
        // Check if the input is empty
        if (argument.isEmpty()) {
            throw AmadeusException.missingArgument("FIND");
        }

        this.keyword = argument.trim().toLowerCase(); // Normalize keyword to lowercase for case-insensitive search
        this.matchingTasks = new ArrayList<>();

        // Filter tasks that contain the keyword in their description
        for (Task task : TaskList.getTaskList())
        {
            if (task.getName().toLowerCase().contains(keyword))
            {
                matchingTasks.add(task);
            }
        }
    }

    /**
     * Executes the command by displaying the tasks that match the keyword.
     */
    @Override
    public void execute()
    {
        if (matchingTasks.isEmpty())
        {
            System.out.printf("⚠️ No tasks found containing the keyword '%s'!\n", keyword);
        } else {
            System.out.printf("🔍 Here are the tasks containing the keyword '%s':\n", keyword);
            Speech.sayList(matchingTasks);
        }
    }
}