package amadeus.cognition;

import amadeus.brain.AmadeusException;
import amadeus.perception.DateConverter;
import amadeus.personality.Speech;
import amadeus.workspace.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class CheckCommand extends Command
{
    ArrayList<Task> filteredTasks = new ArrayList<>(); // List of filtered Tasks based on date
    String preposition = "on"; // on, before or after
    String formattedDate; // String of date inquiry

    public CheckCommand(String argument) throws AmadeusException
    {
        // Check if the input is empty
        if (argument.isEmpty()) {
            // Missing Argument in the Input Exception
            throw AmadeusException.invalidCheck();
        }

        String[] parts = argument.trim().split(" ", 2); // Trim to remove extra spaces

        if (parts.length == 0 || parts[0].isEmpty())
        {
            // Invalid: Empty argument after "check"
            throw AmadeusException.invalidCheck();
        }

        // Assume full input is a date unless proven otherwise
        String dateString = argument.trim();
        boolean isBefore = dateString.startsWith("before");
        boolean isAfter = dateString.startsWith("after");

        // Special case: If it starts with "before" or "after", extract date
        if (isBefore || isAfter)
        {
            String[] splitParts = dateString.split(" ", 2); // Split at first space

            if (splitParts.length < 2)
            {
                // Invalid: "check before" (no date provided)
                throw AmadeusException.invalidCheck();
            }
            dateString = splitParts[1]; // Extract only the date portion
        }

        // Parse the date of inquiry
        Object date = DateConverter.parseDate(dateString);
        // Convert into LocalDateTime
        LocalDateTime checkDateTime = (date instanceof LocalDateTime)
                ? (LocalDateTime) date
                : ((LocalDate) date).atStartOfDay(); // Midnight 00:00

        // Filter tasks based on the type of check
        for (Task task : TaskList.getTaskList())
        {
            LocalDateTime taskDateTime = getTaskDateTime(task);
            if (taskDateTime != null)
            {
                if (isBefore && taskDateTime.isBefore(checkDateTime))
                {
                    filteredTasks.add(task);
                }
                else if (isAfter && taskDateTime.isAfter(checkDateTime))
                {
                    filteredTasks.add(task);
                }
                else if (isExactMatch(taskDateTime, checkDateTime)) // Exact date or date-time
                {
                    filteredTasks.add(task);
                }
            }
        }

        // Determine the appropriate preposition for the message
        if (isBefore) {
            preposition = "before";

        } else if (isAfter) {
            preposition = "after";
        }

        // Format the date for display
        formattedDate = DateConverter.formatDate(date);
    }


    /**
     * Executes the command by printing the desired filtered Task List
     */
    @Override
    public void execute()
    {
        // Display the filtered tasks
        if (filteredTasks.isEmpty())
        {
            System.out.printf("⚠️ No tasks found occurring %s %s!\n", preposition, formattedDate);
        } else {
            System.out.printf("✍️ These are the Tasks occurring %s %s:\n", preposition, formattedDate);
            Speech.sayList(filteredTasks);
        }
    }


    /**
     * Checks if a task's date/time matches the check date/time based on the rules.
     *
     * @param taskDateTime The task's date/time.
     * @param checkDateTime The check date/time.
     * @return True if the task should be included based on the check conditions.
     */
    private boolean isExactMatch(LocalDateTime taskDateTime, LocalDateTime checkDateTime)
    {
        // Extract date parts
        LocalDate taskDate = taskDateTime.toLocalDate();
        LocalDate checkDate = checkDateTime.toLocalDate();

        // Always check if the dates match
        if (!taskDate.equals(checkDate)) {
            return false;
        }

        // Extract time parts
        LocalTime taskTime = taskDateTime.toLocalTime();
        LocalTime checkTime = checkDateTime.toLocalTime();

        // Determine if the checkDateTime has a time component (i.e., user provided time)
        boolean checkHasTime = !checkTime.equals(LocalTime.MIDNIGHT); // Only true if check isn't 00:00

        // If checking with a specific time, match exact tasks and date-only tasks
        if (checkHasTime) {
            return taskTime.equals(checkTime) || taskTime.equals(LocalTime.MIDNIGHT);
        }

        // If checking a date only, match all tasks on that date (regardless of time)
        return true;
    }


    /**
     * Extracts the date and time from a task.
     *
     * @param task The task to extract the date and time from.
     * @return The date and time of the task, or null if the task has no date.
     */
    private LocalDateTime getTaskDateTime(Task task)
    {
        if (task instanceof Deadline)
        {
            Object by = ((Deadline) task).getBy();
            return (by instanceof LocalDateTime)
                    ? (LocalDateTime) by
                    : ((LocalDate) by).atStartOfDay();
        }
        else if (task instanceof Event)
        {
            Object from = ((Event) task).getFrom();
            return (from instanceof LocalDateTime)
                    ? (LocalDateTime) from
                    : ((LocalDate) from).atStartOfDay();
        }
        return null; // ToDo tasks have no date
    }
}