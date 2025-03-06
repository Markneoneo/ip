package amadeus.cognition;

import amadeus.brain.AmadeusException;
import amadeus.personality.Speech;
import amadeus.workspace.Task;
import amadeus.workspace.TaskList;

import java.util.ArrayList;

/**
 * Command to search for tasks in the task list that match a keyword.
 * <p>
 * This command parses the user input to identify the keyword and filters tasks
 * whose descriptions contain the keyword (case-insensitive).
 * </p>
 */
public class FindCommand extends Command {
    private final String keyword; // The keyword to search for
    private final ArrayList<Task> matchingTasks; // List of tasks matching the keyword

    /**
     * Constructs a new {@code FindCommand} by parsing the user input for the keyword.
     * <p>
     * The input is expected to contain a non-empty keyword. If the input is empty,
     * an {@link amadeus.brain.AmadeusException} is thrown.
     * </p>
     *
     * @param argument the user input containing the keyword to search for; must not be {@code null} or empty.
     * @throws AmadeusException if the input is empty or invalid.
     */
    public FindCommand(String argument) throws AmadeusException {
        // Check if the input is empty
        if (argument.isEmpty()) {
            throw AmadeusException.missingArgument("FIND");
        }

        this.keyword = argument.trim().toLowerCase(); // Normalize keyword to lowercase for case-insensitive search
        this.matchingTasks = new ArrayList<>();

        // Filter tasks that contain the keyword in their description
        for (Task task : TaskList.getTaskList()) {
            if (task.getName().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
    }

    /**
     * Executes the command by displaying the tasks that match the keyword.
     * <p>
     * If no tasks match the keyword, a message is displayed to inform the user.
     * Otherwise, the matching tasks are displayed using {@link amadeus.personality.Speech#sayList(java.util.ArrayList)}.
     * </p>
     */
    @Override
    public void execute() {
        if (matchingTasks.isEmpty()) {
            System.out.printf("⚠️ No tasks found containing the keyword '\033[4;1m%s\033[0m'!\n", keyword);
        } else {
            System.out.printf("🔍 Here are the tasks containing the keyword '\033[4;1m%s\033[0m':\n", keyword);
            Speech.sayList(matchingTasks);
        }
    }
}