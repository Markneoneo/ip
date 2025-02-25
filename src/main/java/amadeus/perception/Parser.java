/**
 * Handles the parsing of user input into executable commands.
 * This class interprets the user's input and maps it to the appropriate command object,
 * which can then be executed to perform the desired action.
 */

package amadeus.perception;
import amadeus.brain.AmadeusException;
import amadeus.cognition.*;

public class Parser
{
    /**
     * Constants representing valid user commands.
     * These are used to map user input to the appropriate command object.
     */
    public final static String BYE_COMMAND = "bye";
    public final static String COMMAND_LIST = "commands";
    public final static String LIST_COMMAND = "list";
    public final static String CHECK_COMMAND = "check";
    public final static String MARK_COMMAND = "mark";
    public final static String UNMARK_COMMAND = "unmark";
    public final static String DELETE_COMMAND = "delete";
    public final static String RESET_COMMAND = "reset";
    public final static String DEADLINE_COMMAND = "deadline";
    public final static String EVENT_COMMAND = "event";
    public final static String TODO_COMMAND = "todo";


    /**
     * Parses the user's input and returns the corresponding command object.
     * The input is split into a command and an optional argument. The command is then
     * matched against predefined constants to determine the appropriate action.
     *
     * @param input The full command input provided by the user.
     * @return The corresponding `Command` object.
     * @throws AmadeusException If the command is invalid or the input cannot be parsed.
     */
    public static Command parse(String input) throws AmadeusException
    {
        String[] words = input.split(" ", 2); // Split into command + optional argument
        String command = words[0].toLowerCase(); // Start with command
        String argument = words.length > 1 ? words[1] : "";

        // Match the command to the appropriate Command object
        return switch (command)
        {
            // Exits the command loop and terminates the interaction
            case BYE_COMMAND -> new ByeCommand();

            // Shows the user all possible Amadeus commands
            case COMMAND_LIST -> new CommandList();

            // Displays the current list of tasks to the user
            case LIST_COMMAND -> new ListCommand();

            // Print tasks occurring on / before / after a specific date.
            case CHECK_COMMAND -> new CheckCommand(argument);

            // Resets and Clears the saved Task List
            case RESET_COMMAND -> new ResetCommand();

            // Deletes a given task
            case DELETE_COMMAND -> new DeleteCommand(argument);

            // Marks or unmarks a given task
            case MARK_COMMAND, UNMARK_COMMAND -> new MarkCommand(argument, command.equals(MARK_COMMAND));

            // Adds a new task of the specified type
            case DEADLINE_COMMAND -> new DeadlineCommand(argument);
            case EVENT_COMMAND -> new EventCommand(argument);
            case TODO_COMMAND -> new ToDoCommand(argument);

            // Invalid Command Input Exception
            default -> throw AmadeusException.invalidCommand();
        };
    }
}