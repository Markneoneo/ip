/**
 * Command to add a new Event task to the task list.
 * This command parses the user input to create an Event task and adds it to the list.
 */

package amadeus.cognition;

import amadeus.brain.AmadeusException;
import amadeus.perception.DateConverter;
import amadeus.workspace.Event;
import amadeus.workspace.TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventCommand extends Command
{
    private final Event e;

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

        // Check if "/from" and "/to" exist before splitting
        if (!input.contains(" /from ") || !input.contains(" /to "))
        {
            // Invalid Event Format Exception
            throw AmadeusException.invalidEvent();
        }

        // Split the input into description and from/to
        String[] parts = input.split(" /from ", 2);
        String name = parts[0].trim();

        // Split the from/to part into start and end times
        String[] fromToParts = parts[1].split(" /to ", 2);

        // Parse the start and end times into LocalDateTime objects
        Object from = DateConverter.parseDate(fromToParts[0].trim());
        Object to = DateConverter.parseDate(fromToParts[1].trim());

        // Ensure that from is strictly before to
        if (from instanceof LocalDateTime && to instanceof LocalDateTime)
        {
            if (!((LocalDateTime) from).isBefore((LocalDateTime) to))
            {
                throw AmadeusException.invalidEventTime();
            }
        }
        else if (from instanceof LocalDate && to instanceof LocalDate)
        {
            if (!((LocalDate) from).isBefore((LocalDate) to))
            {
                throw AmadeusException.invalidEventTime();
            }
        } else {
            // Handle case where types don't match
            throw AmadeusException.invalidEvent();
        }

        // Create a new Event task
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
