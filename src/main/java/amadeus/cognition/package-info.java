/**
 * Provides the core command implementations for the Amadeus application.
 * <p>
 * This package includes classes that represent various commands supported by the application,
 * such as adding tasks, marking tasks as complete, searching for tasks, and more. Each command
 * is implemented as a subclass of the {@link amadeus.cognition.Command} abstract class.
 * </p>
 * <p>
 * The classes in this package are responsible for parsing user input, performing the necessary
 * actions, and handling errors gracefully using custom exceptions from the {@link amadeus.brain.AmadeusException} class.
 * </p>
 *
 * <h2>Key Classes</h2>
 * <ul>
 *   <li>{@link amadeus.cognition.Command}: Abstract base class for all commands.</li>
 *   <li>{@link amadeus.cognition.ByeCommand}: Command to exit the application.</li>
 *   <li>{@link amadeus.cognition.CheckCommand}: Command to filter tasks based on a date or date range.</li>
 *   <li>{@link amadeus.cognition.CommandList}: Command to display the list of available commands.</li>
 *   <li>{@link amadeus.cognition.DeadlineCommand}: Command to add a new {@link amadeus.workspace.Deadline} task.</li>
 *   <li>{@link amadeus.cognition.EventCommand}: Command to add a new {@link amadeus.workspace.Event} task.</li>
 *   <li>{@link amadeus.cognition.FindCommand}: Command to search for tasks matching a keyword.</li>
 *   <li>{@link amadeus.cognition.ListCommand}: Command to display the current list of tasks.</li>
 *   <li>{@link amadeus.cognition.MarkCommand}: Command to mark a task as complete or incomplete.</li>
 *   <li>{@link amadeus.cognition.ResetCommand}: Command to reset the task list.</li>
 *   <li>{@link amadeus.cognition.ToDoCommand}: Command to add a new {@link amadeus.workspace.ToDo} task.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>
 * // Create and execute a command
 * Command command = new ToDoCommand("Buy groceries");
 * command.execute();
 * </pre>
 *
 * @see amadeus.cognition.Command
 * @see amadeus.brain.AmadeusException
 * @see amadeus.workspace.Task
 */
package amadeus.cognition;