/**
 * Command to display the list of available commands.
 * This command prints the list of commands to the console, providing usage examples.
 */

package amadeus.cognition;
import amadeus.personality.Speech;

public class CommandList extends Command
{
    /**
     * Displays the list of available commands to the console.
     * The commands are formatted with usage examples for better readability.
     */
    @Override
    public void execute()
    {
        Speech.sayCommands();
    }
}
