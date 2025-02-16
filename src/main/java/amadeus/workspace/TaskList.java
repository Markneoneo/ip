/**
 * Manages the list of tasks in the Amadeus application.
 * This class provides methods to add, mark, delete, and reset tasks, ensuring
 * that changes are saved to the database and communicated to the user via speech.
 */

package amadeus.workspace;
import amadeus.brain.AmadeusException;
import amadeus.brain.Database;
import amadeus.personality.Speech;

import java.util.ArrayList;

public class TaskList
{
    /**
     * ArrayList to store all tasks, categorized by type (Deadline, Event, ToDo, etc.).
     * The list is initialized by loading tasks from the database when the program starts.
     */
    public static ArrayList<Task> taskList;


    /**
     * Loads tasks from the database when the program starts.
     * If an error occurs during loading, a runtime exception is thrown.
     */
    static {
        try {
            taskList = Database.load();

        } catch (AmadeusException e) {
            throw new RuntimeException(e);
        }
    }


    //region Add Tasks to List
    /**
     * Adds a new Deadline task to the task list.
     * The task is inserted at the end of the Deadline section, and the updated list is saved to the database.
     * The user is notified via speech that the task has been added.
     *
     * @param d The Deadline task to add.
     * @throws AmadeusException If an error occurs while saving the updated list to the database.
     */
    public static void addDeadline(Deadline d) throws AmadeusException
    {
        // Insert the Deadline task at the end of the Deadline section
        int index = 0;
        while (index < taskList.size() && taskList.get(index) instanceof Deadline)
        {
            index++;
        }
        taskList.add(index, d);

        // Save the updated list to the database
        Database.save(taskList);

        // Notify the user via speech
        Speech.sayTaskAdded(d, "DEADLINE");
    }


    /**
     * Adds a new Event task to the task list.
     * The task is inserted at the end of the Event section, and the updated list is saved to the database.
     * The user is notified via speech that the task has been added.
     *
     * @param e The Event task to add.
     * @throws AmadeusException If an error occurs while saving the updated list to the database.
     */
    public static void addEvent(Event e) throws AmadeusException
    {
        // Insert the Event task at the end of the Event section
        int index = 0;
        while (index < taskList.size() && (taskList.get(index) instanceof Deadline || taskList.get(index) instanceof Event))
        {
            index++;
        }
        taskList.add(index, e);

        // Save the updated list to the database
        Database.save(taskList);

        // Notify the user via speech
        Speech.sayTaskAdded(e, "EVENT");
    }


    /**
     * Adds a new ToDo task to the task list.
     * The task is inserted at the end of the ToDo section, and the updated list is saved to the database.
     * The user is notified via speech that the task has been added.
     *
     * @param td The ToDo task to add.
     * @throws AmadeusException If an error occurs while saving the updated list to the database.
     */
    public static void addToDo(ToDo td) throws AmadeusException
    {
        // Insert the ToDo task at the end of the ToDo section
        int index = 0;
        while (index < taskList.size() && (taskList.get(index) instanceof Deadline || taskList.get(index) instanceof Event || taskList.get(index) instanceof ToDo))
        {
            index++;
        }
        taskList.add(index, td);

        // Save the updated list to the database
        Database.save(taskList);

        // Notify the user via speech
        Speech.sayTaskAdded(td, "TODO");
    }
    //endregion


    /**
     * Marks a task as complete or incomplete based on the provided index.
     * The updated task status is saved to the database, and the user is notified via speech.
     *
     * @param index  The index of the task to update (1-based).
     * @param status The new completion status (true for complete, false for incomplete).
     * @throws AmadeusException If the index is invalid or an error occurs while saving the updated list.
     */
    public static void markDone(int index, boolean status) throws AmadeusException
    {
        try {
            // Update the task's completion status
            taskList.get(index - 1).updateDone(status);

            // Save the updated list to the database
            Database.save(taskList);

            // Notify the user via speech
            Speech.sayTaskMarked(index, status);

            // Print the updated task details
            taskList.get(index - 1).printTask();

        } catch (IndexOutOfBoundsException e) {
            // Out of Bounds Index Number Exception
            throw AmadeusException.invalidIndex();
        }
    }


    /**
     * Deletes a task from the task list based on the provided index.
     * The updated list is saved to the database, and the user is notified via speech.
     *
     * @param index The index of the task to delete (1-based).
     * @throws AmadeusException If the index is invalid or an error occurs while saving the updated list.
     */
    public static void deleteTask(int index) throws AmadeusException
    {
        try {
            // Remove the task at the specified index
            Task removedTask = taskList.remove(index - 1);

            // Save the updated list to the database
            Database.save(taskList);

            // Notify the user via speech
            Speech.sayTaskDeleted(index);

            // Print the deleted task details
            removedTask.printTask();

        } catch (IndexOutOfBoundsException e) {
            // Out of Bounds Index Number Exception
            throw AmadeusException.invalidIndex();
        }
    }


    /**
     * Resets the task list by clearing all tasks.
     * The updated list is saved to the database, and the user is notified via speech.
     *
     * @throws AmadeusException If an error occurs while saving the updated list.
     */
    public static void resetList() throws AmadeusException
    {
        // Clear all tasks from the list
        taskList.clear();

        // Save the updated list to the database
        Database.save(taskList);

        // Notify the user via speech
        Speech.sayTaskReset();
    }
}
