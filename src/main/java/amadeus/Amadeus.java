/**
 * Handles the user interface (UI) interactions for the Amadeus application.
 * This class provides methods to display login and logout sequences, print text with a typing effect,
 * and show available commands to the user. It also serves as the entry point for the application.
 */

package amadeus;
import amadeus.command.UserInput;

public class Amadeus
{
    private static final String DIVIDER = "=".repeat(100);
    //region Amadeus Logo
    private static final String AMADEUS =
            """
                                                                                   @
                                                                                @@@@
                                                                              @@@@@@
                                                                           @@@@@@#@@
                                                                         @@@@@@@@@@@
                                                                       @@@@@@@  @@@@
                                                                    @@@@@@@:    @@@@
                                                                  @@@@@@@       @@@@
                                                               -@@@@@@#         @@@@
                                                             @@@@@@@            @@@@
                                                           @@@@@@@              @@@@
                                                         @@@@@@=                @@@@
                                                      @@@@@@@                   @@@@
                                                   +@@@@@@=                     @@@@
                                                 @@@@@@@                        @@@@
                                               @@@@@@@                          @@@@
                                             @@@@@@#                            @@@@
                                          @@@@@@@+                              @@@@
                                       @@@@@@@#        @@@@@@@@@@@@@@@@@@@@@@@  @@@@
                                     @%#@*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@    @@@@=@@@@@@@@@@@@@@@@@#-
                                   #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@=+         @@ @@@@@@@@@@@@@@@@%@@@@@@@@
                                 @@%@@@@                                     @@                       @@@
                              @@@@@@@@ @@@@@@@@@@@@@@@ @@@@@@@@@@@ @@@@@@@@@@@@ @@@@@@@@@@@@ @@       @@@ @@@@@@@@@@@
                            @@@#@@@    @@@   @@    @@@         @@@ @@#       @@ @@       @@@ @@       @@@ @@        \s
                         @@@@@@@@      @@@   @@    @@@ @@@@@@@@@@@ @@@       @@ @@@@@@@@@@@@ @@       @@@ @@@@@@@@@@@
                       @@@@@@@         @@@   #@    @@@ @@       @@ @@@       @@ @@           @@       @@%          @@
                     @@@@@@@@@@@@@@@@@@@@@   @@    @@@ @@@@@@@@@@@ @@@@@@@@@@@@ @@@@@@@@@@@@ @@@@@@@@@@@ @@@@@@@@@@@@
                                                   @@-                                         @@@@@@@
                                                   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                                                                                  @@@@@@@@+
                                                                                @@@@@@@@-
                                                                                @@@@@@@
                                                                                @@@@@
                                                                                @@@
                                                                                @
                    """;
    //endregion
    // region Amadeus Command List
    private static final String COMMANDS =
            """
                ✨ Welcome to Amadeus Task Manager! ✨
                Here are the commands you can use to manage your tasks:
               \s
                ➤ ✍️ Adding a Task:
                   ╰┈➤ 'todo 【description】': Adds a new ToDo task.
                        Eg: "todo Buy groceries"
                   ╰┈➤ 'deadline 【description】 /by 【date】': Adds a new Deadline task.
                        Eg: "deadline Submit report /by 2023-10-31"
                   ╰┈➤ 'event 【description】 /from 【start】 /to 【end】': Adds a new Event task.
                        Eg: "event Team meeting /from Mon 2pm /to 4pm"
                   ╰┈➤ '【description】': Adds a generic task.
                        Eg: "Read a book"
               \s
                ➤ 👀 View Tasks:
                   ╰┈➤ 'list': Displays all your tasks, organized by type (Deadlines, Events, ToDos, Misc Tasks).
               \s
                ➤ ✔️ Mark Tasks:
                   ╰┈➤ 'mark 【index】': Marks a task as complete.
                     Example: "mark 1"
                   ╰┈➤ 'unmark 【index】': Marks a task as incomplete.
                     Example: "unmark one"
               \s
                ➤ 🗑️ Delete Tasks:
                   ╰┈➤ 'delete 【index】': Deletes a task from the list.
                     Example: "delete 2"
               \s
                ➤ 📴 Exit:
                   ╰┈➤ 'bye': Exits the application.
               """;
    //endregion

    /**
     * Prints a string to the console with a typing effect, simulating a character-by-character display.
     * This method is useful for creating a more interactive and engaging user experience.
     *
     * @param text     The text to be printed to the console.
     * @param duration The delay (in milliseconds) between printing each character.
     *                A smaller value results in faster typing, while a larger value slows it down.
     */
    public static void printSlow(String text, int duration)
    {
        for (int i = 0; i < text.length(); i++)
        {
            System.out.print(text.charAt(i));
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
            }
        }
    }

    /**
     * Displays the boot sequence when the Amadeus application starts.
     * This method simulates a login process, showing a user ID, loading messages, and an introduction.
     * It uses the `printSlow` method to create a typing effect for a more immersive experience.
     */
    public static void logIn()
    {
        System.out.println(DIVIDER);
        printSlow("User ID: Salieri\nLogging in...\nLaunching...\n", 50);
        printSlow(AMADEUS, 1);
        printSlow("""
                Nice to meet you, I'm Kurisu Makise, a.k.a. Amadeus.
                I look forward to working with you.
                Type "commands" to see what I can do!
                """,10);
        System.out.println(DIVIDER);
    }

    /**
     * Displays the shutdown sequence when the Amadeus application is exiting.
     * This method prints a farewell message with a typing effect, providing a polished and thematic ending.
     */
    public static void logOff()
    {
        printSlow("Goodbye. May our timelines converge once more.\nEl Psy Kongroo\n",30);
        System.out.println(DIVIDER);
    }

    /**
     * Displays the list of available commands to the user.
     * This method uses the `printSlow` method to print the command list with a typing effect,
     * ensuring a consistent and engaging user experience.
     */
    public static void showCommands()
    {
        printSlow(COMMANDS,1);
    }

    /**
     * The entry point of the Amadeus application.
     * This method initializes the application by displaying the login sequence,
     * starting the command loop for user interaction, and finally displaying the logout sequence.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args)
    {
        logIn();            // Display the login sequence
        UserInput.getCommand(); // Start the command loop
        logOff();           // Display the logout sequence
    }
//    input.txt Test Cases:
//    ➤ Empty Task List
//    ➤ Adding a ToDo Task
//    ➤ Adding a ToDo with Missing Description
//
//    ➤ Adding a Deadline Task
//    ➤ Invalid Deadline Format
//    ➤ Adding a Deadline with Missing Description
//
//    ➤ Adding an Event Task
//    ➤ Invalid Event Format
//    ➤ Adding an Event with Missing Description
//
//    ➤ Adding a Generic Task
//    ➤ Marking a Task as Complete
//    ➤ Marking a Task as Complete (Non-Numeric)
//    ➤ Marking a Task as Incomplete (Non-Numeric)
//    ➤ Deleting a Task
//    ➤ Invalid Task Index (Mark/Unmark)
//    ➤ Missing Task Index (Mark/Unmark)
//    ➤ Listing All Tasks
//    ➤ Exiting the Application
}
