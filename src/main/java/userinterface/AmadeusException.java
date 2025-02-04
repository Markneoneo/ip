/**
 * Custom exception class for Amadeus-specific errors.
 * This class extends the built-in Exception class and provides constructors
 * for creating exceptions with custom error messages.
 */

package userinterface;

public class AmadeusException extends Exception
{
    /**
     * Constructs an AmadeusExceptions with a custom error message.
     *
     * @param message The custom error message.
     */
    public AmadeusException(String message)
    {
        super(message);
    }
}
