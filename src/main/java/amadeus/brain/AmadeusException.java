package amadeus.brain;

/**
 * Represents a custom exception class for handling domain-specific errors in the Amadeus application.
 * <p>
 * This class extends {@code Exception} and provides static factory methods to create
 * exceptions for different error scenarios, such as invalid commands, missing arguments,
 * invalid task formats, and database errors.
 * </p>
 */
public class AmadeusException extends Exception {
    /**
     * Constructs an {@code AmadeusException} with a specified error message.
     *
     * @param message The detailed error message explaining the exception.
     */
    public AmadeusException(String message) {
        super(message);
    }

    //region Command & Argument Exceptions

    /**
     * Creates an exception indicating an invalid command was entered.
     * <p>
     * This exception is thrown when the user inputs a command that is not recognized by the application.
     * </p>
     *
     * @return An {@code AmadeusException} with a message prompting the user to view available commands.
     */
    public static AmadeusException invalidCommand() {
        return new AmadeusException("""
                ⚠️ \033[31;1mInvalid Command!\033[0m
                Hmph. It seems you've entered something I don't recognize.
                Type "\033[1;32mcommands\033[0m" to see what I can do.""");
    }

    /**
     * Creates an exception indicating a required argument is missing.
     * <p>
     * This exception is thrown when the user fails to provide a necessary argument, such as a task description.
     * </p>
     *
     * @param type The type of task (e.g., "todo", "deadline", "event") for which the argument is missing.
     * @return An {@code AmadeusException} indicating the missing argument.
     */
    public static AmadeusException missingArgument(String type) {
        return new AmadeusException(String.format("""
                ⚠️ \033[31;1mMissing Argument!\033[0m
                You forgot to provide the \033[1m%s\033[0m task name/description.
                Is your Hippocampus alright?""", type));
    }

    /**
     * Creates an exception indicating an invalid deadline format.
     * <p>
     * This exception is thrown when the user enters a deadline task with an incorrect format.
     * </p>
     *
     * @return An {@code AmadeusException} indicating the correct deadline format.
     */
    public static AmadeusException invalidDeadline() {
        return new AmadeusException("""
                ⚠️ \033[31;1mInvalid Deadline Format!\033[0m
                The correct format is: \033[1m<description> /by <date/time>\033[0m.
                For example: "\033[3;32mdeadline Submit report /by 31/12/2025\033[0m".""");
    }

    /**
     * Creates an exception indicating an invalid event format.
     * <p>
     * This exception is thrown when the user enters an event task with an incorrect format.
     * </p>
     *
     * @return An {@code AmadeusException} indicating the correct event format.
     */
    public static AmadeusException invalidEvent() {
        return new AmadeusException("""
                ⚠️ \033[31;1mInvalid Event Format!\033[0m
                The correct format is: \033[1m<description> /from <start date/time> /to <end date/time>\033[0m.
                For example: "\033[3;32mevent Team meeting /from 1/1/2025 11:59pm /to 2/1/2025 12pm\033[0m".""");
    }

    /**
     * Creates an exception indicating an invalid event time range.
     * <p>
     * This exception is thrown when the event's start time is after its end time.
     * </p>
     *
     * @return An {@code AmadeusException} indicating that the event time range is invalid.
     */
    public static AmadeusException invalidEventTime() {
        return new AmadeusException("""
                ⚠️ \033[31;1mInvalid Event Time!\033[0m
                The start time must be before the end time.
                Time travel isn't possible... yet.""");
    }

    /**
     * Creates an exception indicating an invalid check command format.
     * <p>
     * This exception is thrown when the user enters a check command with an incorrect format.
     * </p>
     *
     * @return An {@code AmadeusException} indicating the correct check command format.
     */
    public static AmadeusException invalidCheck() {
        return new AmadeusException("""
                ⚠️ \033[31;1mInvalid CHECK Format!\033[0m
                The correct format is: \033[1mcheck (Optional: before/after) <date/time>\033[0m.
                For example: "\033[3;32mcheck before 31/12/2025\033[0m".""");
    }

    //endregion

    //region Index & Date Number Exceptions

    /**
     * Creates an exception indicating a missing task index.
     * <p>
     * This exception is thrown when the user does not provide an index for commands like `mark`, `unmark`, or `delete`.
     * </p>
     *
     * @return An {@code AmadeusException} prompting the user to provide a task index.
     */
    public static AmadeusException missingNumber() {
        return new AmadeusException("""
                ⚠️ \033[31;1mMissing Task Index!\033[0m
                Please provide the desired task index number.
                Personally, I like the number 1.048596.""");
    }

    /**
     * Creates an exception indicating an invalid task index format.
     * <p>
     * This exception is thrown when the user provides an index that is not a valid number.
     * </p>
     *
     * @return An {@code AmadeusException} prompting the user to provide a valid numeric task index.
     */
    public static AmadeusException invalidNumber() {
        return new AmadeusException("""
                ⚠️ \033[31;1mInvalid Task Index!\033[0m
                Please provide a valid task index number.
                You do know how to count... right?""");
    }

    /**
     * Creates an exception indicating an out-of-bounds task index.
     * <p>
     * This exception is thrown when the user provides an index that is outside the range of the task list.
     * </p>
     *
     * @return An {@code AmadeusException} indicating that the task index was not found.
     */
    public static AmadeusException invalidIndex() {
        return new AmadeusException("""
                ⚠️ \033[31;1mInvalid Task Index!\033[0m
                The task index you provided doesn't exist.
                Perhaps it does in another timeline.""");
    }

    /**
     * Creates an exception indicating an invalid date format.
     * <p>
     * This exception is thrown when the user provides a date that does not match the expected format.
     * </p>
     *
     * @return An {@code AmadeusException} prompting the user to provide a date in a valid format.
     */
    public static AmadeusException invalidDate() {
        return new AmadeusException("""
                ⚠️ \033[31;1mInvalid Date Format!\033[0m
                \033[4mPlease enter the date and time in one of the following formats:\033[0m
                Date only: \033[32m2/12/2019, 2-12-2019, 2 12 2019\033[0m
                Date & time (24-hour): \033[32mDate 18:00, Date 1800, Date 18.00\033[0m
                Date & time (12-hour): \033[32mDate 6:00PM, Date 6pm, Date 6.30AM\033[0m""");
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
        return new AmadeusException(String.format("""
                ⚠️ \033[31;1mError Saving Tasks!\033[0m
                Something went wrong while saving tasks to the file: %s
                Please check the file and try again.""", file));
    }

    /**
     * Creates an exception for errors while loading tasks from the file.
     * This is thrown when an IOException occurs during the load operation.
     *
     * @param file The error message from the IOException.
     * @return An AmadeusException with a message indicating the error while loading tasks.
     */
    public static AmadeusException errorLoadingTask(String file) {
        return new AmadeusException(String.format("""
                ⚠️ \033[31;1mError Loading Tasks!\033[0m
                Something went wrong while loading tasks from the file: %s
                Please check the file and try again.""", file));
    }

    /**
     * Creates an exception for an unrecognized task type found in the saved file.
     *
     * @param type The invalid task type found (e.g., not "T", "D", or "E").
     * @return An AmadeusException indicating the invalid task type.
     */

    public static AmadeusException invalidTaskType(String type) {
        return new AmadeusException(String.format("⚠️ \033[31;1mInvalid task type\033[0m in file: %s", type));
    }

    /**
     * Creates an exception for errors while parsing a task from the saved file.
     * This is thrown when a line in the file cannot be parsed into a valid task.
     *
     * @param line The line from the file that caused the parsing error.
     * @return An AmadeusException with a message indicating the error while parsing the task.
     */
    public static AmadeusException errorParsingTask(String line) {
        return new AmadeusException(
                String.format("⚠️ \033[31;1mError parsing task\033[0m from line: '%s'. Skipping.", line));
    }

    /**
     * Creates an exception for errors while loading the logo.
     * This is thrown when an IOException occurs during the logo load operation.
     *
     * @param file The file path of the logo that failed to load.
     * @return An {@code AmadeusException} with a message indicating the error while loading the logo.
     */
    public static AmadeusException errorLoadingLogo(String file) {
        return new AmadeusException(String.format("""
            ⚠️ \033[31;1mError Loading Logo!\033[0m
            I was unable to load the logo from: %s
            Perhaps it's lost in time? Check the file path and try again.""", file));
    }
    //endregion
}
