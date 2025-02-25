/**
 * Handles the user interface (UI) and speech interactions for the Amadeus application.
 * This class provides methods to display messages, commands, and task lists to the user,
 * with features like typing effects and formatted text for better readability.
 */

package amadeus.personality;
import amadeus.workspace.*;
import java.util.ArrayList;

public class Speech
{
    // \033[1m Text \033[0m , [1m = Bold , [3m = Italicize
    /**
     * A string of "=" characters used as a visual divider in the console output.
     */
    private static final String DIVIDER = "=".repeat(100);

    /**
     * A large ASCII art representation of the Amadeus logo.
     * Used to display a visually appealing welcome message.
     */
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

    /**
     * A formatted string listing all available commands and their usage.
     * Includes examples and styling (bold and italic) for better readability.
     */
    // region Amadeus Command List
    private static final String COMMANDS =
            """
                ✨ Welcome to Amadeus Task Manager! ✨
                Here are the commands you can use to manage your tasks:
               \s
                ➤ ✍️ Adding a Task:
                   ╰┈➤ \033[1mtodo 【description】\033[0m: Adds a new ToDo task.
                        Eg: "\033[3mtodo Buy groceries\033[0m"
                   ╰┈➤ \033[1mdeadline 【description】 /by 【date/time】\033[0m: Adds a new Deadline task.
                        Eg: "\033[3mdeadline Submit report /by 31/12/2025\033[0m"
                   ╰┈➤ \033[1mevent 【description】 /from 【start date/time】 /to 【end date/time】\033[0m: Adds a new Event task.
                        Eg: "\033[3mevent Team meeting /from 1/1/2025 11:59pm /to 2/1/2025 12pm\033[0m"
               \s
                ➤ 👀 View Tasks:
                   ╰┈➤ \033[1mlist\033[0m: Displays all your saved tasks, organized by type.
                   ╰┈➤ \033[1mcheck 【date/time】\033[0m: Lists tasks on a specified date/time.
                        Eg: "\033[3mcheck 2/10/2025\033[0m"
                   ╰┈➤ \033[1mcheck before 【date/time】\033[0m: Lists tasks before a specified date/time (inclusive).
                        Eg: "\033[3mcheck before 2/10/2025 6am\033[0m"
                   ╰┈➤ \033[1mcheck after 【date/time】\033[0m: Lists tasks after a specified date/time (inclusive).
                        Eg: "\033[3mcheck after 2/10/2025 2359\033[0m"
               \s
                ➤ ✔️ Mark Tasks:
                   ╰┈➤ \033[1mmark 【index】\033[0m: Marks a task as complete.
                        Eg: "\033[3mmark 1\033[0m"
                   ╰┈➤ \033[1munmark 【index】\033[0m: Marks a task as incomplete.
                        Eg: "\033[3munmark 1\033[0m"
               \s
                ➤ 🗑️ Delete Tasks:
                   ╰┈➤ \033[1mdelete 【index】\033[0m: Deletes a task from the list.
                        Eg: "\033[3mdelete 2\033[0m"
                   ╰┈➤ \033[1mreset\033[0m: Resets and deletes the entire saved list.
               \s
                ➤ 📴 Exit:
                   ╰┈➤ \033[1mbye\033[0m: Exits the application.
               \s
                ➤ ℹ️ Formating Notice:
                   ╰┈➤ \033[1mIndex Numbers\033[0m: I am able to parse both words and integers.
                        Eg: "\033[3mmark one, delete thirteen\033[0m"
                   ╰┈➤ \033[1mDate Format\033[0m: I am strictly only allowing dd/mm/yyyy format. Sorry Americans!
                        Eg: "\033[3m31/12/2025, 31-12-2025, 31 12 2025\033[0m"
                   ╰┈➤ \033[1mTime Format\033[0m: I am able to parse both 12 (AM/PM) & 24 Hour formats.
                        Eg: "\033[3m12am, 11:59pm, 11.59pm, 2359, 23:59, 23.59\033[0m"
              \s""";
    //endregion\


    /**
     * Prints a visual divider line to the console.
     * This is used to separate sections of the output for better readability.
     */
    public static void sayLine() { System.out.println(DIVIDER); }


    /**
     * Prints text to the console with a typing effect, simulating a character-by-character display.
     * This method is useful for creating a more interactive and engaging user experience.
     *
     * @param text     The text to be printed to the console.
     * @param duration The delay (in milliseconds) between printing each character.
     *                A smaller value results in faster typing, while a larger value slows it down.
     */
    public static void saySlowly(String text, int duration)
    {
        // Print each character with a delay to simulate a typing effect
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
     * Displays the welcome message when the Amadeus application starts.
     * This includes the Amadeus logo, a login sequence, and an introduction.
     */
    public static void sayWelcome()
    {
        System.out.println(DIVIDER);
        saySlowly("User ID: \033[1mSalieri\033[0m\n\033[3mLogging in...\033[0m\n\033[3mLaunching...\033[0m\n", 50);
        saySlowly(AMADEUS, 1); // Amadeus Logo
        saySlowly("""
                Nice to meet you, I'm Kurisu Makise, a.k.a. \033[1mAmadeus\033[0m.
                I look forward to working with you.
                Type "\033[1mcommands\033[0m" to see what I can do!
                """,10);
        System.out.println(DIVIDER);
    }


    /**
     * Displays the farewell message when the Amadeus application is exiting.
     * This includes a thematic goodbye message and the Amadeus catchphrase.
     */
    public static void sayGoodbye()
    {
        saySlowly("Goodbye. May our timelines converge once more.\n\033[1mEl Psy Kongroo\033[0m\n",30);
        System.out.println(DIVIDER);
    }


    /**
     * Displays the list of available commands to the user.
     * The commands are formatted with styling (bold and italic) for better readability.
     */
    public static void sayCommands()
    {
        saySlowly(COMMANDS,1);
    }


    /**
     * Displays the current list of tasks to the user, organized by type (Deadlines, Events, ToDos).
     * If the list is empty, a message indicating no pending tasks is shown.
     *
     * @param taskList The list of tasks to display.
     */
    public static void sayList(ArrayList<Task> taskList)
    {
        // Check if the task list is empty
        if (taskList.isEmpty()) {
            System.out.println("⚠️ There are currently no pending tasks!");
        } else {
            System.out.printf("✍️ You currently have 【%s】 pending tasks!\n\n", taskList.size());
        }

        int index = 0;
        // Track which section titles have been printed
        // Index 0: Deadlines, Index 1: Events, Index 2: ToDos, Index 3: Misc Tasks
        boolean[] titlePrinted = new boolean[4];

        for (Task task : taskList)
        {
            if (task instanceof Deadline && !titlePrinted[0])
            {
                System.out.println("⚠️\033[1mDEADLINES\033[0m⚠️");
                titlePrinted[0] = true; // Mark Deadlines title as printed
            }
            else if (task instanceof Event && !titlePrinted[1])
            {
                if (index > 0) { System.out.println(); } // Add a newline before the section
                System.out.println("\uD83C\uDF38\033[1mEVENTS\033[0m\uD83C\uDF38");
                titlePrinted[1] = true; // Mark Events title as printed
            }
            else if (task instanceof ToDo && !titlePrinted[2])
            {
                if (index > 0) { System.out.println(); } // Add a newline before the section
                System.out.println("\uD83D\uDCCB\033[1mTODO LIST\033[0m\uD83D\uDCCB");
                titlePrinted[2] = true; // Mark ToDos title as printed
            }

            // Print out task
            index++;
            System.out.print(index + ". ");
            task.printTask();
        }
    }


    /**
     * Notifies the user that a task has been successfully added to the list.
     * The task type and details are displayed with formatting for better readability.
     *
     * @param task     The task that was added.
     * @param taskType The type of the task (e.g., "DEADLINE", "EVENT", "TODO").
     */
    public static void sayTaskAdded(Task task, String taskType)
    {
        System.out.printf("✍️ Understood! The following \033[1m%s\033[0m has been stored:\n╰┈➤ %s%n", taskType, task);
    }


    /**
     * Notifies the user that a task has been marked as complete or incomplete.
     * The task index and new status are displayed with formatting for better readability.
     *
     * @param index  The index of the task that was marked.
     * @param status The new completion status (true for complete, false for incomplete).
     */
    public static void sayTaskMarked(int index, boolean status)
    {
        System.out.printf("✍️ Understood, the following task has been set to %s\n╰┈➤ %d. ",
                status ? "\033[1mComplete\033[0m ✔️. Well Done!" : "\033[1mIncomplete\033[0m ❌. Keep it up!", index);
    }


    /**
     * Notifies the user that a task has been successfully deleted from the list.
     * The task index is displayed with formatting for better readability.
     *
     * @param index The index of the task that was deleted.
     */
    public static void sayTaskDeleted(int index)
    {
        System.out.printf("🗑️ Understood, the following task has been deleted:\n╰┈➤ %s. ", index);
    }


    /**
     * Notifies the user that all tasks have been reset and the task list is now empty.
     */
    public static void sayTaskReset()
    {
        System.out.println("🗑️ All tasks have been deleted. The task list is now empty.");
    }
}
