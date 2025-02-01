// Class to store tasks and how to interact with them
import java.util.ArrayList;

public class TaskList
{
    public static ArrayList<Task> taskList = new ArrayList<Task>();

    // Stores task into the list
    public static void storeTask(String name)
    {
        Task t = new Task(name);
        taskList.add(t);
    }

    // Prints out all stored tasks
    public static void printTaskList()
    {
        for (int i = 1; i <= taskList.size(); i++)
        {
            System.out.print(i + ". ");
            taskList.get(i - 1).printTask();
        }
    }
}
