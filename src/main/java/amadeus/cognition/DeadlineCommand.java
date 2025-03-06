package amadeus.cognition;

import amadeus.brain.AmadeusException;
import amadeus.perception.DateConverter;
import amadeus.workspace.Deadline;
import amadeus.workspace.TaskList;

/**
 * Command to add a new {@link amadeus.workspace.Deadline} task to the task list.
 * <p>
 * This command parses the user input to create a {@link amadeus.workspace.Deadline} task and adds it to the list.
 * The input is expected to contain a description and a due date, separated by {@code /by}.
 * </p>
 */
public class DeadlineCommand extends Command {
    Deadline d;

    /**
     * Constructs a new {@code DeadlineCommand} by parsing the user input.
     * <p>
     * The input is expected to contain a description and a due date, separated by {@code /by}.
     * If the input is empty or in an invalid format, an {@link amadeus.brain.AmadeusException} is thrown.
     * </p>
     *
     * @param input the user input containing the task description and due date; must not be {@code null} or empty.
     * @throws AmadeusException if the input is empty or in an invalid format.
     */
    public DeadlineCommand(String input) throws AmadeusException {
        // Check if the input is empty
        if (input.isEmpty()) {
            // Missing Argument in the Input Exception
            throw AmadeusException.missingArgument("DEADLINE");
        }

        // Check if "/by" exist before splitting
        if (!input.contains(" /by ")) {
            // Invalid Deadline Format Exception
            throw AmadeusException.invalidDeadline();
        }

        // Split the input into description and due date
        String[] parts = input.split(" /by ", 2);
        String name = parts[0].trim();

        // Parse the due date into a LocalDateTime object
        Object by = DateConverter.parseDate(parts[1].trim());

        // Create a new Deadline task
        d = new Deadline(name, by);
    }

    /**
     * Executes the command by adding the {@link amadeus.workspace.Deadline} task to the task list.
     *
     * @throws AmadeusException if an error occurs while adding the task.
     */
    @Override
    public void execute() throws AmadeusException {
        TaskList.addDeadline(d);
    }
}
