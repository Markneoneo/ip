package amadeus;

import amadeus.brain.AmadeusException;
import amadeus.cognition.Command;
import amadeus.perception.Parser;
import amadeus.personality.Speech;

import java.util.Scanner;

/**
 * The main entry point for the Amadeus chatbot system.
 * <p>
 * This class handles user input, processes commands, and manages the execution flow of the chatbot.
 * It initializes the interaction loop, continuously listens for user commands, and executes the
 * corresponding actions until the exit command is received.
 * </p>
 * <p>
 * The chatbot uses the following components:
 * <ul>
 *   <li>{@link amadeus.personality.Speech}: For user interaction and displaying messages.</li>
 *   <li>{@link amadeus.perception.Parser}: For parsing user input into executable commands.</li>
 *   <li>{@link amadeus.cognition.Command}: For executing the parsed commands.</li>
 * </ul>
 */
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
     * <p>
     * This method displays a welcome message and continuously listens for user commands.
     * Each command is parsed and executed until the exit command is received. Errors during
     * command execution are caught and displayed to the user.
     * </p>
     * <p>
     * The interaction loop includes the following steps:
     * <ol>
     *   <li>Read user input from the console.</li>
     *   <li>Parse the input into a {@link amadeus.cognition.Command} object.</li>
     *   <li>Execute the command.</li>
     *   <li>Check if the command is an exit command.</li>
     * </ol>
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
     * <p>
     * This method starts the chatbot by calling the {@link #awaken()} method.
     * </p>
     *
     * @param args command-line arguments (not used in this application).
     */
    public static void main(String[] args)
    {
        awaken();
    }
}
