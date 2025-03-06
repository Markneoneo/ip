package amadeus.personality;

import amadeus.brain.AmadeusException;
import amadeus.workspace.Deadline;
import amadeus.workspace.Event;
import amadeus.workspace.Task;
import amadeus.workspace.ToDo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the user interface (UI) and speech interactions for the Amadeus application.
 * <p>
 * This class provides methods to display messages, commands, and task lists to the user,
 * with features like typing effects and formatted text for better readability. It also
 * includes visual elements such as dividers and ASCII art for an enhanced user experience.
 * </p>
 */
public class Speech {
    /**
     * A string of "=" characters used as a visual divider in the console output.
     */
    private static final String DIVIDER = "=".repeat(100);

    /**
     * Variable to hold the Amadeus logo content.
     * This is used to display a visually appealing welcome message.
     * The logo is loaded from an external file during class initialization.
     */
    private static String AMADEUS;

    // Static block to initialize the Amadeus logo from a file
    static {
        try (InputStream inputStream = Speech.class.getResourceAsStream("amadeus_logo.txt")) {
            if (inputStream == null) {
                throw AmadeusException.errorLoadingLogo("amadeus_logo.txt");
            }
            try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
                AMADEUS = scanner.useDelimiter("\\A").next(); // Read the entire file
            }
        } catch (IOException | AmadeusException e) {
            AMADEUS = "Amadeus Logo"; // Fallback in case of error
            System.err.println(AmadeusException.errorLoadingLogo("amadeus_logo.txt").getMessage());
        }
    }

    /**
     * A formatted string listing all available commands and their usage.
     * Includes examples and styling  for better readability.
     */
    // region Amadeus Command List
    private static final String COMMANDS =
            """
                      ✨ \033[1;38;2;255;165;0mWelcome to Amadeus Task Manager!\033[0m ✨
                      Here are the commands you can use to manage your tasks:
                     \s
                      ➤ ✍️ \033[4;1mAdding a Task\033[0m:
                         ╰┈➤ \033[35;1mtodo 【description】\033[0m: Adds a new ToDo task.
                              Eg: "\033[32;3mtodo Buy groceries\033[0m"
                         ╰┈➤ \033[35;1mdeadline 【description】 /by 【due date】\033[0m: Adds a new Deadline task.
                              Eg: "\033[32;3mdeadline Submit report /by 31/12/2025\033[0m"
                         ╰┈➤ \033[35;1mevent 【description】 /from 【start】 /to 【end】\033[0m: Adds a new Event task.
                              Eg: "\033[32;3mevent Team meeting /from 1/1/2025 11:59pm /to 2/1/2025 12pm\033[0m"
                     \s
                      ➤ 👀 \033[4;1mViewing Tasks\033[0m:
                         ╰┈➤ \033[35;1mlist\033[0m: Displays all your saved tasks, organized by type.
                         ╰┈➤ \033[35;1mfind 【description】\033[0m: Lists tasks with given keyword.
                              Eg: "\033[32;3mfind meeting\033[0m"
                         ╰┈➤ \033[35;1mcheck 【date/time】\033[0m: Lists tasks on a specified date/time.
                              Eg: "\033[32;3mcheck 2/10/2025\033[0m"
                         ╰┈➤ \033[35;1mcheck before 【date/time】\033[0m: Lists tasks before a specified date/time (inclusive).
                              Eg: "\033[32;3mcheck before 2/10/2025 6am\033[0m"
                         ╰┈➤ \033[35;1mcheck after 【date/time】\033[0m: Lists tasks after a specified date/time (inclusive).
                              Eg: "\033[32;3mcheck after 2/10/2025 2359\033[0m"
                     \s
                      ➤ ✔️ \033[4;1mMarking Tasks\033[0m:
                         ╰┈➤ \033[35;1mmark 【index】\033[0m: Marks a task as complete.
                              Eg: "\033[32;3mmark 1\033[0m"
                         ╰┈➤ \033[35;1munmark 【index】\033[0m: Marks a task as incomplete.
                              Eg: "\033[32;3munmark 1\033[0m"
                     \s
                      ➤ 🗑️ \033[4;1mDeleting Tasks\033[0m:
                         ╰┈➤ \033[35;1mdelete 【index】\033[0m: Deletes a task from the list.
                              Eg: "\033[32;3mdelete 2\033[0m"
                         ╰┈➤ \033[35;1mreset\033[0m: Resets and deletes the entire saved list.
                     \s
                      ➤ 📴 \033[4;1mLogging Off\033[0m:
                         ╰┈➤ \033[35;1mbye\033[0m: Exits the application.
                     \s
                      ➤ ℹ️ \033[4;1mFormating Notice\033[0m:
                         ╰┈➤ \033[35;1mIndex Numbers\033[0m: I am able to parse both words and integers.
                              Eg: "\033[32;3mmark one, delete thirteen\033[0m"
                         ╰┈➤ \033[35;1mDate Format\033[0m: I am strictly only allowing dd/mm/yyyy format. Sorry Americans!
                              Eg: "\033[32;3m31/12/2025, 31-12-2025, 31 12 2025\033[0m"
                         ╰┈➤ \033[35;1mTime Format\033[0m: I am able to parse both 12 (AM/PM) & 24 Hour formats.
                              Eg: "\033[32;3m12am, 11:59pm, 11.59pm, 2359, 23:59, 23.59\033[0m"
                    """;
    //endregion


    /**
     * Prints a visual divider line to the console.
     * This is used to separate sections of the output for better readability.
     */
    public static void sayLine() {
        System.out.println(DIVIDER);
    }


    /**
     * Prints text to the console with a typing effect, simulating a character-by-character display.
     * This method is useful for creating a more interactive and engaging user experience.
     *
     * @param text     the text to be printed to the console; must not be {@code null}.
     * @param duration the delay (in milliseconds) between printing each character.
     *                 A smaller value results in faster typing, while a larger value slows it down.
     */
    public static void saySlowly(String text, int duration) {
        // Print each character with a delay to simulate a typing effect
        for (int i = 0; i < text.length(); i++) {
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
     * This includes:
     * <ul>
     *   <li>A simulated login sequence</li>
     *   <li>The Amadeus ASCII logo</li>
     *   <li>An introduction by the AI assistant</li>
     * </ul>
     */
    public static void sayWelcome() {
        System.out.println(DIVIDER);
        saySlowly("""
                User ID: \033[31;1mSalieri\033[0m
                \033[32;3mAuthenticating...\033[0m
                \033[32;3mInitializing system...\033[0m
                """, 50);
        System.out.println(AMADEUS); // Amadeus Logo loaded from file
        saySlowly("""
            \033[34;1mGreetings.\033[0m I'm \033[31;1mKurisu Makise\033[0m, a.k.a. \033[1;38;2;255;165;0mAmadeus\033[0m.
            I'm here to assist you with your tasks. Let's make this timeline a productive one!
            Type "\033[32;1mcommands\033[0m" to see what I can do.
            """, 20);
        System.out.println(DIVIDER);
    }


    /**
     * Displays the farewell message when the Amadeus application is exiting.
     * This includes a thematic goodbye message and the Amadeus catchphrase.
     */
    public static void sayGoodbye() {
        saySlowly("""
                \033[34;1mFarewell.\033[0m May our timelines converge once more.
                \033[91;1mEl Psy Kongroo.\033[0m
                """, 30);
        System.out.println(DIVIDER);
    }


    /**
     * Displays the list of available commands to the user.
     * The commands are formatted with styling for better readability.
     */
    public static void sayCommands() {
        saySlowly(COMMANDS, 1);
    }


    /**
     * Displays the current list of tasks to the user, organized by type (Deadlines, Events, ToDos).
     * If the list is empty, a message indicating no pending tasks is shown.
     *
     * @param taskList the list of tasks to display; must not be {@code null}.
     */
    public static void sayList(ArrayList<Task> taskList) {
        // Check if the task list is empty
        if (taskList.isEmpty()) {
            System.out.println("⚠️ There are currently no pending tasks! Lucky you.");
        } else {
            System.out.printf(
                    "✍️ \033[1;38;2;255;165;0mYou currently have 【%s】 pending tasks! Time to get to work!\033[0m\n\n",
                    taskList.size());
        }

        int index = 0;
        // Track which section titles have been printed
        // Index 0: Deadlines, Index 1: Events, Index 2: ToDos, Index 3: Misc Tasks
        boolean[] titlePrinted = new boolean[4];

        for (Task task : taskList) {
            if (task instanceof Deadline && !titlePrinted[0]) {
                System.out.println("⚠️\033[1;4;93mDEADLINES\033[0m⚠️");
                titlePrinted[0] = true; // Mark Deadlines title as printed
            } else if (task instanceof Event && !titlePrinted[1]) {
                if (index > 0) {
                    System.out.println();
                } // Add a newline before the section
                System.out.println("\uD83C\uDF38\033[1;4;38;2;255;183;197mEVENTS\033[0m\uD83C\uDF38");
                titlePrinted[1] = true; // Mark Events title as printed
            } else if (task instanceof ToDo && !titlePrinted[2]) {
                if (index > 0) {
                    System.out.println();
                } // Add a newline before the section
                System.out.println("\uD83D\uDCCB\033[1;4mTODO LIST\033[0m\uD83D\uDCCB");
                titlePrinted[2] = true; // Mark ToDos title as printed
            }

            // Print the task with aligned columns
            index++;
            String taskDescription = task.getName();
            String taskDetails = task.getDetails() + (task.getDone() ? " ✔️" : "");

            // Use fixed-width columns for alignment
            // %-10s ensures left alignment with a width of 10 characters.
            System.out.printf("%d. %-10s %-30s%n", index, taskDescription, taskDetails);
        }
    }


    /**
     * Notifies the user that a task has been successfully added to the list.
     * The task type and details are displayed with formatting for better readability.
     *
     * @param task     the task that was added; must not be {@code null}.
     * @param taskType the type of the task (e.g., "DEADLINE", "EVENT", "TODO"); must not be {@code null}.
     */
    public static void sayTaskAdded(Task task, String taskType) {
        System.out.printf("""
                ✍️ Understood! The following \033[1m%s\033[0m has been \033[92;1mSaved\033[0m:
                ╰┈➤ %s
                """, taskType, task);
    }


    /**
     * Notifies the user that a task has been marked as complete or incomplete.
     * The task index and new status are displayed with formatting for better readability.
     *
     * @param index  the index of the task that was marked.
     * @param status the new completion status (true for complete, false for incomplete).
     */
    public static void sayTaskMarked(int index, boolean status) {
        System.out.printf("""
                ✍️ Understood! the following task has been set to %s
                ╰┈➤ %d.\s""", status ? "\033[92;1mComplete\033[0m ✔️. Excellent work!" :
                "\033[91;1mIncomplete\033[0m ❌. Don't give up!", index);
    }


    /**
     * Notifies the user that a task has been successfully deleted from the list.
     * The task index is displayed with formatting for better readability.
     *
     * @param index The index of the task that was deleted.
     */
    public static void sayTaskDeleted(int index) {
        System.out.printf("""
                🗑️ Understood! the following task has been \033[1;91mDeleted\033[0m:
                ╰┈➤ %s.\s""", index);
    }


    /**
     * Notifies the user that all tasks have been reset and the task list is now empty.
     */
    public static void sayTaskReset() {
        System.out.println("""
                🗑️ All tasks have been \033[1;91mDeleted\033[0m. The task list is now empty.
                Did you just send a D-mail to the past?""");
    }
}
