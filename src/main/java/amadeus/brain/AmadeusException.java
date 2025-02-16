/**
 * Custom exception class for Amadeus-specific errors.
 * This class extends the built-in `Exception` class and provides static factory methods
 * to create exceptions for different error scenarios, such as invalid commands, missing arguments,
 * invalid task formats, and database errors.
 */

package amadeus.brain;

public class AmadeusException extends Exception
{
    /**
     * Constructs an AmadeusException with a custom error message.
     *
     * @param message The custom error message.
     */
    public AmadeusException(String message)
    {
        super(message);
    }

    //region Command & Argument Exceptions
    /**
     * Creates an exception for invalid commands.
     * This is thrown when the user enters a command that is not recognized.
     *
     * @return An AmadeusException with a message prompting the user to view available commands.
     */
    public static AmadeusException invalidCommand() {
        return new AmadeusException("⚠️ Invalid Command! Please type 'commands' to see what I can do.");
    }

    /**
     * Creates an exception for missing arguments in user input.
     * This is thrown when a required argument (e.g., task description) is missing.
     *
     * @param type The type of task (e.g., "todo", "deadline", "event") for which the argument is missing.
     * @return An AmadeusException with a message prompting the user to provide the missing argument.
     */
    public static AmadeusException missingArgument(String type) {
        return new AmadeusException(String.format("⚠️ Please provide the \033[1m%s\033[0m task name/description!", type));
    }

    /**
     * Creates an exception for invalid deadline formats.
     * This is thrown when the user enters a deadline task in an incorrect format.
     *
     * @return An AmadeusException with a message explaining the correct deadline format.
     */
    public static AmadeusException invalidDeadline() {
        return new AmadeusException("⚠️ Invalid \033[1mDEADLINE\033[0m format! Use: <description> /by <date>");
    }

    /**
     * Creates an exception for invalid event formats.
     * This is thrown when the user enters an event task in an incorrect format.
     *
     * @return An AmadeusException with a message explaining the correct event format.
     */
    public static AmadeusException invalidEvent() {
        return new AmadeusException("⚠️ Invalid \033[1mEVENT\033[0m format! Use: <description> /from <start> /to <end>");
    }
    //endregion

    //region Index Number Exceptions
    /**
     * Creates an exception for missing task indices.
     * This is thrown when the user does not provide an index for commands like `mark`, `unmark`, or `delete`.
     *
     * @return An AmadeusException with a message prompting the user to provide a task index.
     */
    public static AmadeusException missingNumber() {
        return new AmadeusException("⚠️ Missing Task Index! Please provide desired number.");
    }

    /**
     * Creates an exception for invalid task indices.
     * This is thrown when the user provides an index that is not a valid number.
     *
     * @return An AmadeusException with a message prompting the user to provide a valid number.
     */
    public static AmadeusException invalidNumber() {
        return new AmadeusException("⚠️ Invalid Task Index! Please provide a valid number.");
    }

    /**
     * Creates an exception for out-of-bounds task indices.
     * This is thrown when the user provides an index that does not exist in the task list.
     *
     * @return An AmadeusException with a message indicating that the task index was not found.
     */
    public static AmadeusException invalidIndex() {
        return new AmadeusException("⚠️ Task Index not found on the list! Please try again.");
    }
    //endregion

    //region Database Exceptions
    /**
     * Creates an exception for errors while saving tasks to the file.
     * This is thrown when an IOException occurs during the save operation.
     *
     * @param file The error message from the IOException.
     * @return An AmadeusException with a message indicating the error while saving tasks.
     */
    public static AmadeusException errorSavingTask(String file) {
        return new AmadeusException(String.format("⚠️ Error saving tasks to file: %s\n", file));
    }

    /**
     * Creates an exception for errors while loading tasks from the file.
     * This is thrown when an IOException occurs during the load operation.
     *
     * @param file The error message from the IOException.
     * @return An AmadeusException with a message indicating the error while loading tasks.
     */
    public static AmadeusException errorLoadingTask(String file) {
        return new AmadeusException(String.format("⚠️ Error loading tasks from file: %s\n", file));
    }

    /**
     * Creates an exception for invalid task types in the saved file.
     * This is thrown when a task type in the file is not recognized (e.g., not "T", "D", or "E").
     *
     * @param type The invalid task type found in the file.
     * @return An AmadeusException with a message indicating the invalid task type.
     */
    public static AmadeusException invalidTaskType(String type) {
        return new AmadeusException(String.format("⚠️ Invalid task type in file: %s", type));
    }

    /**
     * Creates an exception for errors while parsing a task from the saved file.
     * This is thrown when a line in the file cannot be parsed into a valid task.
     *
     * @param line The line from the file that caused the parsing error.
     * @return An AmadeusException with a message indicating the error while parsing the task.
     */
    public static AmadeusException errorParsingTask(String line) {
        return new AmadeusException(String.format("⚠️ Error parsing task from line: '%s'. Skipping.", line));
    }
    //endregion
}
