package amadeus.cognition;

import amadeus.brain.AmadeusException;

/**
 * Abstract base class for all commands in the Amadeus application.
 * <p>
 * This class defines the structure for commands, including the {@link #execute()} method
 * that must be implemented by subclasses and the {@link #isBye()} method to check if the command
 * is a termination command.
 * </p>
 */
public abstract class Command
{
    /**
     * Executes the command.
     * <p>
     * This method must be implemented by subclasses to perform the specific action
     * associated with the command.
     * </p>
     *
     * @throws AmadeusException if an error occurs during execution.
     */
    public abstract void execute() throws AmadeusException;

    /**
     * Checks if the command is a termination command (e.g., {@code bye}).
     * <p>
     * This method can be overridden by subclasses to indicate that the command
     * should terminate the application.
     * </p>
     *
     * @return {@code true} if the command is a termination command, {@code false} otherwise.
     */
    public boolean isBye()
    {
        return false;
    }
}
