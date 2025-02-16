/**
 * The Amadeus class serves as the main entry point for the Amadeus chatbot system.
 * It handles user input, processes commands, and manages the execution flow.
 * The chatbot remains active in a loop until the exit command is received.
 */

package amadeus;
import amadeus.brain.AmadeusException;
import amadeus.cognition.Command;
import amadeus.perception.Parser;
import amadeus.personality.Speech;
import java.util.Scanner;

public class Amadeus
{
    /**
     * Scanner object for reading user input from the console.
     */
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Flag to track whether the chatbot should terminate.
     */
    private static boolean isExit = false;

    /**
     * Awakens the Amadeus chatbot, initializing the interaction loop.
     * The chatbot continuously listens for user commands, processes them,
     * and executes the corresponding actions until the exit command is received.
     */
    public static void awaken()
    {
        // Display Amadeus Login Sequence
        Speech.sayWelcome();

        // Keep running until the exit command is given
        while (!isExit)
        {
            try // Start the command loop
            {
                // Read user input from console
                String input = scanner.nextLine();
                // Print a separating line for clarity
                Speech.sayLine();
                // Parse the input into a Command object
                Command c = Parser.parse(input);
                // Execute the command
                c.execute();
                // Check if the user wants to exit
                isExit = c.isBye();

            } catch (AmadeusException e) {
                // Display any command-related errors
                System.out.println(e.getMessage());

            } finally {
                // Print a separator line before next input (except during goodbye)
                if (!isExit) {
                    Speech.sayLine();
                }
            }
        }
    }

    /**
     * The main method that serves as the program's entry point.
     * It starts the chatbot by calling the awaken() method.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args)
    {
        awaken();
    }

// region Sample Test Cases (for input.txt testing)
// These test cases represent different user interactions with the chatbot.
//
// ➤ Empty Task List                            - Verify behavior with no tasks
// ➤ Adding a ToDo Task                         - Ensure ToDo tasks are added correctly
// ➤ Adding a ToDo with Missing Description     - Check error handling for missing input
//
// ➤ Adding a Deadline Task                     - Test adding a deadline task
// ➤ Invalid Deadline Format                    - Ensure error handling for wrong format
// ➤ Adding a Deadline with Missing Description - Validate missing input scenario
//
// ➤ Adding an Event Task                       - Test adding an event task
// ➤ Invalid Event Format                       - Ensure error handling for wrong format
// ➤ Adding an Event with Missing Description   - Validate missing input scenario
//
// ➤ Adding a Generic Task                      - Check for handling of generic task additions
// ➤ Marking a Task as Complete                 - Verify marking functionality
// ➤ Marking a Task as Complete (Non-Numeric)   - Ensure error handling for invalid input
// ➤ Marking a Task as Incomplete (Non-Numeric) - Validate incorrect unmarking input
// ➤ Deleting a Task                            - Test task deletion
// ➤ Invalid Task Index (Mark/Unmark)           - Ensure proper handling of out-of-range indexes
// ➤ Missing Task Index (Mark/Unmark)           - Validate missing index scenario
// ➤ Listing All Tasks                          - Check if task list is displayed correctly
// ➤ Exiting the Application                    - Ensure the chatbot terminates correctly
// endregion

}
