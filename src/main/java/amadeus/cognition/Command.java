/**
 * Abstract base class for all commands in the Amadeus application.
 * This class defines the structure for commands, including the `execute` method
 * that must be implemented by subclasses and the `isBye` method to check if the command
 * is a termination command.
 */

package amadeus.cognition;
import amadeus.brain.AmadeusException;

public abstract class Command
{
    /**
     * Executes the command.
     * This method must be implemented by subclasses to perform the specific action
     * associated with the command.
     *
     * @throws AmadeusException If an error occurs during execution.
     */
    public abstract void execute() throws AmadeusException;

    /**
     * Checks if the command is a termination command (e.g., `bye`).
     * This method can be overridden by subclasses to indicate that the command
     * should terminate the application.
     *
     * @return `true` if the command is a termination command, `false` otherwise.
     */
    public boolean isBye()
    {
        return false;
    }
}
