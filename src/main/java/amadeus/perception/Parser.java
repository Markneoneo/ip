package amadeus.perception;

import amadeus.brain.AmadeusException;
import amadeus.cognition.ByeCommand;
import amadeus.cognition.CheckCommand;
import amadeus.cognition.Command;
import amadeus.cognition.CommandList;
import amadeus.cognition.DeadlineCommand;
import amadeus.cognition.DeleteCommand;
import amadeus.cognition.EventCommand;
import amadeus.cognition.FindCommand;
import amadeus.cognition.ListCommand;
import amadeus.cognition.MarkCommand;
import amadeus.cognition.ResetCommand;
import amadeus.cognition.ToDoCommand;

/**
 * Handles the parsing of user input into executable commands.
 * <p>
 * This class interprets the user's input and maps it to the appropriate {@link amadeus.cognition.Command} object,
 * which can then be executed to perform the desired action. It supports a variety of commands, including
 * adding tasks, marking tasks as complete, searching for tasks, and more.
 * </p>
 */
public class Parser {
    // region Constants representing valid user commands.
    /**
     * Constant representing the "bye" command.
     */
    public static final String BYE_COMMAND = "bye";
    /**
     * Constant representing the "commands" command.
     */
    public static final String COMMAND_LIST = "commands";
    /**
     * Constant representing the "list" command.
     */
    public static final String LIST_COMMAND = "list";
    /**
     * Constant representing the "find" command.
     */
    public static final String FIND_COMMAND = "find";
    /**
     * Constant representing the "check" command.
     */
    public static final String CHECK_COMMAND = "check";
    /**
     * Constant representing the "mark" command.
     */
    public static final String MARK_COMMAND = "mark";
    /**
     * Constant representing the "unmark" command.
     */
    public static final String UNMARK_COMMAND = "unmark";
    /**
     * Constant representing the "delete" command.
     */
    public static final String DELETE_COMMAND = "delete";
    /**
     * Constant representing the "reset" command.
     */
    public static final String RESET_COMMAND = "reset";
    /**
     * Constant representing the "deadline" command.
     */
    public static final String DEADLINE_COMMAND = "deadline";
    /**
     * Constant representing the "event" command.
     */
    public static final String EVENT_COMMAND = "event";
    /**
     * Constant representing the "todo" command.
     */
    public static final String TODO_COMMAND = "todo";
    // endregion


    /**
     * Parses the user's input and returns the corresponding {@link amadeus.cognition.Command} object.
     * <p>
     * The input is split into a command and an optional argument. The command is then
     * matched against predefined constants to determine the appropriate action.
     * </p>
     *
     * @param input the full command input provided by the user; must not be {@code null}.
     * @return the corresponding {@link amadeus.cognition.Command} object.
     * @throws AmadeusException if the command is invalid or the input cannot be parsed.
     */
    public static Command parse(String input) throws AmadeusException {
        String[] words = input.split(" ", 2); // Split into command + optional argument
        String command = words[0].toLowerCase(); // Start with command
        String argument = words.length > 1 ? words[1] : "";

        // Match the command to the appropriate Command object
        return switch (command) {
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

        // Finds a task by searching for a keyword in the task description
        case FIND_COMMAND -> new FindCommand(argument);

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
