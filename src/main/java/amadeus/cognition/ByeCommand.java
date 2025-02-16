/**
 * Command to exit the Amadeus application.
 * This command displays a farewell message and terminates the application.
 */

package amadeus.cognition;
import amadeus.personality.Speech;

public class ByeCommand extends Command
{
    /**
     * Displays a farewell message and terminates the application.
     */
    @Override
    public void execute()
    {
        Speech.sayGoodbye();
    }

    /**
     * Indicates that this command is a termination command.
     *
     * @return `true` to indicate that this command should terminate the application.
     */
    @Override
    public boolean isBye()
    {
        return true;
    }
}
