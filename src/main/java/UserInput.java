// Class to process each user input and their respective commands
import java.util.Scanner; // Methods for input

public class UserInput
{
    public final static String BYE_COMMAND = "bye";
    public final static String LIST_COMMAND = "list";
    public static final String DIVIDER = "=".repeat(100);

    public static Scanner in = new Scanner(System.in);

    // Receive the User Command
    public static void getCommand()
    {
        String input;
        do {
            input = in.nextLine(); // User Input
            System.out.println(DIVIDER);
            scanInput(input); // Execute Command
            System.out.println(DIVIDER);

        } while(!(input.equalsIgnoreCase(BYE_COMMAND)));
        Amadeus.logOff();
    }

    // Executes the User Input Command
    public static void scanInput(String input)
    {
        switch (input.toLowerCase())
        {
            case BYE_COMMAND: // Log Off
                break;

            case LIST_COMMAND: // Print Task List
                TaskList.printTaskList();
                break;

            default: // Add Task
                TaskList.storeTask(input);
                System.out.println("added: " + input);
                break;
        }
    }

    //region Obsolete
    public static void echo()
    {
        String input;
        Scanner in = new Scanner(System.in);
        do
        {
            input = in.nextLine(); // User Input
            System.out.println(DIVIDER);
            System.out.println(input);
            System.out.println(DIVIDER);
        } while(!(input.equals("bye")));
        // Log Off at "bye"
        Amadeus.logOff();
    }
    //endregion
}
