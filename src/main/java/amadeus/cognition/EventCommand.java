/**
 * Command to add a new Event task to the task list.
 * This command parses the user input to create an Event task and adds it to the list.
 */

package amadeus.cognition;

import amadeus.brain.AmadeusException;
import amadeus.workspace.Event;
import amadeus.workspace.TaskList;

public class EventCommand extends Command
{
    Event e;

    /**
     * Constructs a new `EventCommand` by parsing the user input.
     * The input is expected to contain a description, a start time, and an end time,
     * separated by "/from" and "/to".
     *
     * @param input The user input containing the task description, start time, and end time.
     * @throws AmadeusException If the input is empty or in an invalid format.
     */
    public EventCommand(String input) throws AmadeusException
    {
        // Check if the input is empty
        if (input.isEmpty()) {
            // Missing Argument in the Input Exception
            throw AmadeusException.missingArgument("EVENT");
        }

        // Split the input into description and from/to
        String[] parts = input.split(" /from ", 2);
        String name = parts[0].trim();

        // Split the from/to part into start and end times
        String[] fromToParts = parts[1].split(" /to ", 2);

        // Check if the input is in the correct format
        if (parts.length != 2 || fromToParts.length != 2) // Missing from/to
        {
            // Invalid Event Format Exception
            throw AmadeusException.invalidEvent();
        }

        // Create a new Event task
        String from = fromToParts[0].trim();
        String to = fromToParts[1].trim();
        e = new Event(name, from, to);
    }

    /**
     * Executes the command by adding the Event task to the task list.
     *
     * @throws AmadeusException If an error occurs while adding the task.
     */
    @Override
    public void execute() throws AmadeusException
    {
        TaskList.addEvent(e);
    }
}
