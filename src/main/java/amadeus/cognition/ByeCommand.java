package amadeus.cognition;

import amadeus.personality.Speech;

/**
 * Command to exit the Amadeus application.
 * <p>
 * This command displays a farewell message and terminates the application.
 * </p>
 */
public class ByeCommand extends Command {
    /**
     * Displays a farewell message and terminates the application.
     * <p>
     * The farewell message is provided by the {@link Speech#sayGoodbye()} method.
     * </p>
     */
    @Override
    public void execute() {
        Speech.sayGoodbye();
    }

    /**
     * Indicates that this command is a termination command.
     *
     * @return {@code true} to indicate that this command should terminate the application.
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
