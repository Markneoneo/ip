/**
 * Class to store tasks and how to interact with them
 */

import java.util.ArrayList;

public class TaskList
{
    public static ArrayList<Task> taskList = new ArrayList<Task>();

    /**
     * Stores the task into the list
     * @param name String set as the task name
     */
    public static void storeTask(String name)
    {
        Task t = new Task(name);
        taskList.add(t);
    }

    /**
     * Updates the completion status of a specific task
     * @param index List index of the task to update
     * @param status Updated status of completion
     */
    public static void markDone(int index, boolean status)
    {
        try {
            taskList.get(index - 1).updateDone(status);
            System.out.print("Understood, the following task has been set to: "
                    + (status ? "[Done]. Well Done!" : "[Not Done]. Keep it up!")
                    + "\nUpdated: ");
            taskList.get(index - 1).printTask();

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task Index not found! Please try again.");
        }
    }

    /**
     * Prints out the stored task list
     */
    public static void printTaskList()
    {
        for (int i = 1; i <= taskList.size(); i++)
        {
            System.out.print(i + ". ");
            taskList.get(i - 1).printTask();
        }
    }
}
