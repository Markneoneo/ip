/**
 * Class for the unique user input commands
 */

import java.util.Scanner; // Methods for input

public class UserInput
{
    // Constants for commands
    public final static String BYE_COMMAND = "bye";
    public final static String LIST_COMMAND = "list";
    public final static String MARK_COMMAND = "mark";
    public final static String UNMARK_COMMAND = "unmark";
    public static final String DIVIDER = "=".repeat(100);

    public static Scanner in = new Scanner(System.in);

    /**
     * Core input loop for user interaction
     * Only exits when receiving BYE_COMMAND
     */
    public static void getCommand()
    {
        String input;
        do {
            input = in.nextLine(); // User Input
            System.out.println(DIVIDER);
            scanInput(input); // Execute Command
            System.out.println(DIVIDER);

        } while(!(input.equalsIgnoreCase(BYE_COMMAND)));
    }

    /**
     * Extracts the task index from the argument, converting words to numbers if necessary
     * @param argument The argument containing the task index
     * @return The task index as an integer, or -1 if invalid
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
     * Scans the user input and executes their respective commands
     * @param input Command input by user
     */
    public static void scanInput(String input)
    {
        String[] words = input.split(" ", 2); // Split into command + optional argument
        String command = words[0].toLowerCase();
        String argument = words.length > 1 ? words[1] : ""; // The rest of the input

        switch (command)
        {
            case BYE_COMMAND: // Log Off
                break;

            case LIST_COMMAND: // Print Task List
                TaskList.printTaskList();
                break;

            case MARK_COMMAND: // Mark a task as done
            case UNMARK_COMMAND: // Unmark a task
                if (!argument.isEmpty())
                {
                    int index = extractIndex(argument); // Get Task Index
                    if (index != -1)
                    {
                        TaskList.markDone(index, command.equals(MARK_COMMAND)); // Update status
                    } else {
                        System.out.println("Invalid task index! Please provide a valid number."); // Invalid Number
                    }
                } else {
                    System.out.println("Please provide the task index to update its completion status!"); // No Number
                }
                break;

            default: // Add Task
                TaskList.storeTask(input);
                System.out.println("added: " + input);
                break;
        }
    }

    //region Obsolete
    public static void echo()
    {
        String input;
        Scanner in = new Scanner(System.in);
        do
        {
            input = in.nextLine(); // User Input
            System.out.println(DIVIDER);
            System.out.println(input);
            System.out.println(DIVIDER);
        } while(!(input.equals("bye")));
        // Log Off at "bye"
        Amadeus.logOff();
    }
    //endregion
}
