package amadeus.brain;

import amadeus.perception.DateConverter;
import amadeus.workspace.Deadline;
import amadeus.workspace.Event;
import amadeus.workspace.Task;
import amadeus.workspace.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the storage and retrieval of tasks in the Amadeus application.
 * <p>
 * This class handles saving tasks to a file and loading tasks from a file,
 * ensuring data persistence across application sessions. It supports three types of tasks:
 * {@link ToDo}, {@link Deadline}, and {@link Event}.
 * </p>
 */
public class Database {
    /**
     * The file path where tasks are saved and loaded from.
     * <p>
     * The default file path is {@code "Memory.txt"}.
     * </p>
     */
    public final static String FILE_PATH = "Memory.txt";


    /**
     * Saves the current list of tasks to a file.
     * <p>
     * Each task is converted to a file-friendly format using the {@link Task#toFileFormat()} method
     * and written to the file specified by {@link #FILE_PATH}.
     * </p>
     *
     * @param tasks the list of tasks to save; must not be {@code null}.
     * @throws AmadeusException if an error occurs while saving tasks to the file.
     */
    public static void save(ArrayList<Task> tasks) throws AmadeusException {
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
            // Error Saving Task List to Memory.txt
            throw AmadeusException.errorSavingTask(e.getMessage());
        }
    }


    /**
     * Parses a line from the file into a {@link Task} object.
     * <p>
     * The line is expected to be in a specific format, depending on the task type:
     * <ul>
     *   <li><b>ToDo</b>: {@code T | <isDone> | <description>}</li>
     *   <li><b>Deadline</b>: {@code D | <isDone> | <description> | <by>}</li>
     *   <li><b>Event</b>: {@code E | <isDone> | <description> | <from> | <to>}</li>
     * </ul>
     *
     * @param line the line from the file to parse; must not be {@code null}.
     * @return the {@link Task} object corresponding to the line.
     * @throws AmadeusException if the line is invalid or cannot be parsed.
     */
    public static Task parseSave(String line) throws AmadeusException {
        try {
            // Split the line into parts using the delimiter " | "
            String[] parts = line.split(" \\| ");

            String type = parts[0]; // Task type (T, D, or E)
            boolean isDone = parts[1].equals("1"); // Completion status
            String description = parts[2]; // Task description

            // Create the appropriate Task object based on the type
            switch (type) {
            case "D": // Deadline
                // Parse the due date into a LocalDateTime object
                Object by = DateConverter.parseDate(parts[3].trim());
                return new Deadline(description, isDone, by);

            case "E": // Event
                // Parse the start and end times into LocalDateTime objects
                Object from = DateConverter.parseDate(parts[3].trim());
                Object to = DateConverter.parseDate(parts[4].trim());
                return new Event(description, isDone, from, to);

            case "T": // ToDo
                return new ToDo(description, isDone);

            default: // Invalid Saved Task Type Exception
                throw AmadeusException.invalidTaskType(type);
            }
        } catch (Exception e) {
            // Error parsing a Saved Task
            throw AmadeusException.errorParsingTask(line);
        }
    }


    /**
     * Loads tasks from the file and returns them as a list of {@link Task} objects.
     * <p>
     * If the file does not exist, an empty list is returned. If the file exists,
     * each line is parsed into a {@link Task} object using the {@link #parseSave(String)} method.
     * </p>
     *
     * @return a list of tasks loaded from the file; never {@code null}.
     * @throws AmadeusException if an error occurs while loading tasks from the file.
     */
    public static ArrayList<Task> load() throws AmadeusException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            // Open the file for reading
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); // Read each line
                Task task = parseSave(line); // parse into Task object
                tasks.add(task); // Add to Task List
            }

            // Close the file
            scanner.close();

        } catch (FileNotFoundException e) { // If the file is not found, start with an empty task list
            System.out.println("⚠️ No existing task file found. Starting with an empty task list.");

        } catch (Exception e) {
            // Error Loading Task List from Memory.txt
            throw AmadeusException.errorLoadingTask(e.getMessage());
        }

        return tasks;
    }
}
