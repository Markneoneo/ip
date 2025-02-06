/**
 * Handles file operations for the Amadeus application, including saving tasks to a file
 * and loading tasks from a file. This class ensures that tasks are persisted across
 * application sessions by storing them in a text file.
 */

package amadeus.command;
import amadeus.task.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Database
{
    public final static String FILE_PATH = "Amadeus.txt";

    /**
     * Saves the current list of tasks to a file in a specific format.
     * The file is stored at the path specified by `FILE_PATH`.
     *
     * @param tasks The list of tasks to save. Each task is converted to a file-friendly
     *              format using the `toFileFormat` method.
     */
    public static void saveTasks(ArrayList<Task> tasks)
    {
        try {
            // Open the file for writing
            FileWriter writer = new FileWriter(FILE_PATH);

            // Write each task to the file
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n");
            }

            // Close the file
            writer.close();

        } catch (IOException e) {
            System.out.println("⚠️Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from a file and returns them as a list of `Task` objects.
     * If the file does not exist, an empty list is returned. If the file exists,
     * each line is parsed into a `Task` object using the `parseTask` method.
     *
     * @return A list of tasks loaded from the file. If the file is empty or invalid,
     *         an empty list is returned.
     */
    public static ArrayList<Task> loadTasks()
    {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            // Open the file for reading
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);

            // Read each line and parse it into a Task object
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                Task task = parseTask(line);

                if (task != null) {
                    tasks.add(task);
                }
            }

            // Close the file
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("⚠️No existing task file found. Starting with an empty task list.");
        } catch (Exception e) {
            System.out.println("⚠️Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Parses a line from the file into a `Task` object.
     * The line is expected to be in a specific format, depending on the task type:
     * - Misc.: `M | <isDone> | <description>`
     * - ToDo: `T | <isDone> | <description>`
     * - Deadline: `D | <isDone> | <description> | <by>`
     * - Event: `E | <isDone> | <description> | <from> | <to>`
     *
     * @param line The line from the file to parse.
     * @return The `Task` object corresponding to the line, or `null` if the line is invalid.
     */
    private static Task parseTask(String line)
    {
        try {
            // Split the line into parts using the delimiter " | "
            String[] parts = line.split(" \\| ");

            String type = parts[0]; // Task type (T, D, or E)
            boolean isDone = parts[1].equals("1"); // Completion status
            String description = parts[2]; // Task description

            // Create the appropriate Task object based on the type
            switch (type)
            {
                case "D": // Deadline
                    String by = parts[3]; // Due date
                    return new Deadline(description, isDone, by);
                case "E": // Event
                    String from = parts[3]; // Start time
                    String to = parts[4]; // End time
                    return new Event(description, isDone, from, to);
                case "T": // ToDo
                    return new ToDo(description, isDone);
                case "M": // Misc.
                    return new Task(description, isDone);
                default:
                    System.out.println("⚠️Invalid task type in file: " + type);
                    return null;
            }
        } catch (Exception e) {
            System.out.println("⚠️Error parsing task from line: '" + line + "'. Skipping.");
            return null;
        }
    }
}
