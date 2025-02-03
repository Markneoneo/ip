/**
 * Represents a scheduled event task with a start and end time.
 * Inherits from the {@link Task} class.
 */

package tasktypes;

public class Event extends Task
{
    // The start date/time of the event.
    protected String from;
    // The end date/time of the event.
    protected String to;

    /**
     * Constructs an Event task with a name, start time, and end time.
     *
     * @param name The name or description of the event.
     * @param from The start date/time of the event.
     * @param to   The end date/time of the event.
     */
    public Event(String name, String from, String to)
    {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event, including its name,
     * start time, and end time.
     *
     * @return A formatted string representing the event details.
     */
    @Override
    public String toString()
    {
        return name + " \uD83D\uDE80From:【" + from + "】 \uD83D\uDEA9To:【" + to + "】";
    }
}
