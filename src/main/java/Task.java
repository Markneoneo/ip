/**
 * Abstract Class for basic tasks
 * Current Properties: Name, Completion Status
 */

public class Task
{
    protected String name;
    protected boolean isDone;

    /**
     * Constructor for Tasks: Name, Completion Status
     * Task is set to incomplete by default
     * @param input String to be set as the task name
     */
    public Task(String input)
    {
        this.name = input;
        this.isDone = false;
    }

    /**
     * Sets the name of the task to the given input
     * @param input String to be set as the task name
     */
    public void setName(String input)
    {
        this.name = input;
    }

    /**
     * Returns the name of the task
     * @return String of the task name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Checks the completion status of the task
     * @return True if complete, false if incomplete
     */
    public boolean getDone()
    {
        return isDone;
    }

    /**
     * Updates the completion status of the task to complete
     * @param status True if complete, false if incomplete
     */
    public void updateDone(boolean status)
    {
        this.isDone = status;
    }


    /**
     * Prints the name and completion status of the task
     */
    public void printTask()
    {
        System.out.print(this.name + (isDone ? " [✓]" : "") + "\n");
    }
}
