/**
 * Handles user input and command processing for the application.
 * This class manages the parsing and execution of user commands, such as adding tasks,
 * marking tasks as done, and listing tasks. It also provides a loop for continuous
 * user interaction until the exit command is received.
 */

package amadeus.command;
import amadeus.Amadeus;

import java.util.Scanner; // Methods for input

public class UserInput
{
    // Constants representing valid user commands
    public final static String COMMAND_LIST = "commands";
    public final static String BYE_COMMAND = "bye";
    public final static String LIST_COMMAND = "list";
    public final static String MARK_COMMAND = "mark";
    public final static String UNMARK_COMMAND = "unmark";
    public final static String DELETE_COMMAND = "delete";
    public final static String DEADLINE_COMMAND = "deadline";
    public final static String EVENT_COMMAND = "event";
    public final static String TODO_COMMAND = "todo";
    public static final String DIVIDER = "=".repeat(100);

    public static Scanner in = new Scanner(System.in);

    /**
     * Continuously reads user input from the console and processes commands.
     * The loop runs until the user mentions the exit command 'bye'.
     * Each command is executed using the `scanInput` method.
     */
    public static void getCommand()
    {
        String input;
        do {
            input = in.nextLine(); // User Input
            System.out.println(DIVIDER);

            try {
                scanInput(input); // Execute Command
            } catch (AmadeusException e) {
                System.out.println(e.getMessage()); // Print custom error message
            }

            if (!input.toLowerCase().startsWith(BYE_COMMAND)) {
                System.out.println(DIVIDER);
            }
        } while(!input.toLowerCase().startsWith(BYE_COMMAND));
    }

    /**
     * Extracts a task index from the provided argument.
     * The argument can be either a numeric string (e.g., "1") or a word representing a number (e.g., "one").
     * If the argument is invalid or cannot be converted to a number, -1 is returned.
     *
     * @param argument The string containing the task index (numeric or word format).
     * @return The task index as an integer, or -1 if the argument is invalid.
     */
    private static int extractIndex(String argument)
    {
        try {
            // Try parsing the argument as a number directly
            return Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            // If parsing fails, try converting words to a number
            return NumberConverter.wordToNumber(argument);
        }
    }

    /**
     * Parses and executes the user's command based on the input string.
     * The input is split into a command and an optional argument. The command is then
     * matched against predefined constants to determine the appropriate action.
     * If the command requires an argument (e.g., marking a task), the argument is validated
     * and processed accordingly. Invalid or missing arguments result in error messages.
     *
     * @param input The full command input provided by the user.
     * @throws AmadeusException If the command or input is invalid.
     */
    public static void scanInput(String input) throws AmadeusException
    {
        String[] words = input.split(" ", 2); // Split into command + optional argument
        String command = words[0].toLowerCase();
        String argument = words.length > 1 ? words[1] : "";

        switch (command)
        {
            // Shows the user all possible Amadeus commands
            case COMMAND_LIST:
                Amadeus.showCommands();
                break;

            // Exits the command loop and terminates the interaction.
            case BYE_COMMAND:
                break;

            // Displays the current list of tasks to the user.
            case LIST_COMMAND:
                TaskList.printTaskList();
                break;

            // Marks, unmarks or deletes a task as done based on the provided task index.
            // Displays an error if the index is missing or invalid.
            case MARK_COMMAND:
            case UNMARK_COMMAND:
            case DELETE_COMMAND:
                if (argument.isEmpty()) { // Missing Number
                    throw new AmadeusException("⚠️Missing Task Index! Please provide desired number.");
                }
                int index = extractIndex(argument); // Get Task Index

                if (index == -1) { // Invalid Number
                    throw new AmadeusException("⚠️Invalid Task Index! Please provide a valid number.");
                }

                if (command.equals(DELETE_COMMAND)) {
                    TaskList.deleteTask(index);
                }
                else { // Update status
                    TaskList.markDone(index, command.equals(MARK_COMMAND));
                }
                break;

            // Adds a new task of the specified type (deadline, event, or todo) using the provided argument.
            // Displays an error if the task description is missing.
            case DEADLINE_COMMAND:
            case EVENT_COMMAND:
            case TODO_COMMAND:
                if (argument.isEmpty()) { // Missing Argument
                    throw new AmadeusException("⚠️Please provide the " + command.toLowerCase() + " task name/description!");
                }
                switch (command) {
                    case DEADLINE_COMMAND -> TaskList.storeDeadline(argument);
                    case EVENT_COMMAND -> TaskList.storeEvent(argument);
                    case TODO_COMMAND -> TaskList.storeToDo(argument);
                }
                break;

            // Adds a generic task using the full input as the task description.
            default:
                TaskList.storeTask(input);
                break;
        }
    }
}
