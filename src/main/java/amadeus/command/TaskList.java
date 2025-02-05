/**
 * Manages the storage, organization, and manipulation of tasks in the application.
 * This class provides methods to add, update, and display tasks, ensuring they are
 * categorized and displayed in a structured manner (e.g., Deadlines, Events, ToDos).
 * Tasks are stored in an ArrayList and can be marked as complete or incomplete.
 */

package amadeus.command;
import amadeus.task.*;
import java.util.ArrayList;

public class TaskList
{
    // ArrayList to store all tasks, categorized by type (Deadline, Event, ToDo, etc.).
    public static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds a new Deadline task to the task list.
     * The input string is expected to contain a description and a due date, separated by "/by".
     * The task is inserted into the appropriate section of the list (after existing Deadlines).
     *
     * @param input The user input containing the task description and due date (e.g., "return book /by Sunday").
     * @throws AmadeusException If the input format is invalid.
     */
    public static void storeDeadline(String input) throws AmadeusException
    {
        String[] parts = input.split(" /by ", 2); // Split into description and date

        if (parts.length != 2)
        {
            throw new AmadeusException("⚠️Invalid deadline format! Use: <description> /by <date>");
        }

        String name = parts[0].trim();
        String by = parts[1].trim();
        Deadline d = new Deadline(name, by);

        int index = 0;
        while (index < taskList.size() && taskList.get(index) instanceof Deadline)
        {
            index++;
        }

        taskList.add(index, d); // Insert at the end of the Deadline section
        System.out.println("✍️Understood! The following Deadline has been stored:\n╰┈➤ " + d);
    }

    /**
     * Adds a new Event task to the task list.
     * The input string is expected to contain a description, a start time, and an end time,
     * separated by "/from" and "/to". The task is inserted into the appropriate section of
     * the list (after existing Deadlines and Events).
     *
     * @param input The user input containing the task description, start time, and end time
     *              (e.g., "project meeting /from Mon 2pm /to 4pm").
     * @throws AmadeusException If the input format is invalid or cannot be parsed.
     */
    public static void storeEvent(String input) throws AmadeusException
    {
        Event e = getEvent(input);

        int index = 0;
        while (index < taskList.size() && (taskList.get(index) instanceof Deadline || taskList.get(index) instanceof Event))
        {
            index++;
        }

        taskList.add(index, e); // Insert at the end of the Event section
        System.out.println("✍️Understood! The following Event has been stored:\n╰┈➤ " + e);
    }

    /**
     * Parses the input string to create a new Event task.
     * The input string is expected to contain a description, a start time, and an end time,
     * separated by "/from" and "/to". If the input format is invalid, an AmadeusException is thrown.
     *
     * @param input The user input containing the task description, start time, and end time
     *              (e.g., "project meeting /from Mon 2pm /to 4pm").
     * @return A new Event object created from the parsed input.
     * @throws AmadeusException If the input format is invalid (e.g., missing "/from" or "/to").
     */
    private static Event getEvent(String input) throws AmadeusException
    {
        String[] parts = input.split(" /from ", 2); // Split into description and from/to
        String name = parts[0].trim();
        String[] fromToParts = parts[1].split(" /to ", 2); // Split into from and to

        if (parts.length != 2 || fromToParts.length != 2)
        {
            throw new AmadeusException("⚠️Invalid event format! Use: <description> /from <start> /to <end>");
        }

        String from = fromToParts[0].trim();
        String to = fromToParts[1].trim();
        return new Event(name, from, to);
    }

    /**
     * Adds a new ToDo task to the task list.
     * The task is inserted into the appropriate section of the list (after existing Deadlines,
     * Events, and ToDos).
     *
     * @param name The description or name of the ToDo task.
     */
    public static void storeToDo(String name)
    {
        ToDo td = new ToDo(name);

        int index = 0;
        while (index < taskList.size() && (taskList.get(index) instanceof Deadline || taskList.get(index) instanceof Event || taskList.get(index) instanceof ToDo))
        {
            index++;
        }

        taskList.add(index, td); // Insert at the end of the ToDo section
        System.out.println("✍️Understood! The following ToDo has been stored:\n╰┈➤ " + td);
    }

    /**
     * Adds a generic Task to the end of the task list.
     * This method is used for tasks that do not fall into the Deadline, Event, or ToDo categories.
     *
     * @param name The description or name of the task.
     */
    public static void storeTask(String name)
    {
        Task t = new Task(name);
        taskList.add(t); // Add generic tasks at the end
        System.out.println("✍️Understood! The following Task has been stored:\n╰┈➤ " + t);
    }


    /**
     * Updates the completion status of a specific task in the list.
     * The task is identified by its index, and its status is updated to either complete or incomplete.
     * If the index is invalid, an error message is displayed.
     *
     * @param index  The index of the task to update (1-based).
     * @param status The new completion status (true for complete, false for incomplete).
     * @throws AmadeusException If the task index is invalid.
     */
    public static void markDone(int index, boolean status) throws AmadeusException
    {
        try {
            taskList.get(index - 1).updateDone(status);

            System.out.print("✍️Understood, the following task has been set to"
                    + (status ? "〚Complete〛✔️. Well Done!" : "〚Incomplete〛❌. Keep it up!")
                    + "\n╰┈➤ " + index + ". ");

            taskList.get(index - 1).printTask();

        } catch (IndexOutOfBoundsException e) {
            throw new AmadeusException("⚠️Task Index not found! Please try again.");
        }
    }


    /**
     * Displays all tasks in the list, organized by category (Deadlines, Events, ToDos, Misc Tasks).
     * Each task is printed with its index and details. If the list is empty, a message indicating
     * no pending tasks is shown.
     */
    public static void printTaskList()
    {
        if (taskList.isEmpty())
        {
            System.out.println("⚠️There are currently no pending tasks!");
        } else {
            System.out.println("✍️You currently have 【" + taskList.size() + "】 pending tasks!\n");
        }

        int index = 0;
        boolean[] titlePrinted = new boolean[4]; // Boolean array to track printed titles
        // Index 0: Deadlines, Index 1: Events, Index 2: ToDos, Index 3: Misc Tasks

        for (Task task : taskList)
        {
            if (task instanceof Deadline && !titlePrinted[0])
            {
                System.out.println("⚠️DEADLINES⚠️");
                titlePrinted[0] = true; // Mark Deadlines title as printed
            }
            else if (task instanceof Event && !titlePrinted[1])
            {
                if (index > 0) { System.out.println(); } // Add a newline before the section
                System.out.println("\uD83C\uDF38EVENTS\uD83C\uDF38");
                titlePrinted[1] = true; // Mark Events title as printed
            }
            else if (task instanceof ToDo && !titlePrinted[2])
            {
                if (index > 0) { System.out.println(); } // Add a newline before the section
                System.out.println("\uD83D\uDCCBTODO LIST\uD83D\uDCCB");
                titlePrinted[2] = true; // Mark ToDos title as printed
            }
            else if (!(task instanceof Deadline || task instanceof Event || task instanceof ToDo) && !titlePrinted[3])
            {
                if (index > 0) { System.out.println(); } // Add a newline before the section
                System.out.println("\uD83D\uDEE0️MISC TASKS\uD83D\uDEE0️");
                titlePrinted[3] = true; // Mark Misc Tasks title as printed
            }

            index++;
            System.out.print(index + ". ");
            task.printTask();
        }
    }
}
