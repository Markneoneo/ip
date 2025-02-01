// Class for basic tasks
// Current Properties: Name

public class Task
{
    protected String name;

    // Constructor
    public Task(String input)
    {
        this.name = input;
    }

    // Task Name Setter
    public void setName(String input)
    {
        this.name = input;
    }

    // Task Name Getter
    public String getName()
    {
        return this.name;
    }

    // Prints Task Name
    public void printTask()
    {
        System.out.print(this.name + "\n");
    }
}
