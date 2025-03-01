/**
 * Provides the core task management functionality for the Amadeus application.
 * <p>
 * This package includes classes that represent tasks and manage the task list. It supports
 * different types of tasks, such as {@link ToDo}, {@link Deadline}, and {@link Event}, and
 * provides methods to add, mark, delete, and reset tasks. The {@link TaskList} class ensures
 * that changes to the task list are saved to the database and communicated to the user.
 * </p>
 *
 * <h2>Key Classes</h2>
 * <ul>
 *   <li>{@link amadeus.workspace.Task}: Abstract base class representing a generic task.</li>
 *   <li>{@link amadeus.workspace.TaskList}: Manages the list of tasks and provides methods to
 *       add, mark, delete, and reset tasks.</li>
 *   <li>{@link amadeus.workspace.ToDo}: Represents a simple task with no specific date or time constraints.</li>
 *   <li>{@link amadeus.workspace.Deadline}: Represents a task with a specific deadline.</li>
 *   <li>{@link amadeus.workspace.Event}: Represents a scheduled event task with a start and end time.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>
 * // Create and add tasks to the task list
 * TaskList.addToDo(new ToDo("Buy groceries"));
 * TaskList.addDeadline(new Deadline("Submit report", LocalDate.of(2025, 12, 31)));
 * TaskList.addEvent(new Event("Team meeting", LocalDateTime.of(2025, 1, 1, 11, 59), LocalDateTime.of(2025, 1, 2, 12, 0)));
 *
 * // Mark a task as complete
 * TaskList.markDone(1, true);
 *
 * // Delete a task
 * TaskList.deleteTask(2);
 *
 * // Reset the task list
 * TaskList.resetList();
 * </pre>
 *
 * @see amadeus.workspace.Task
 * @see amadeus.workspace.TaskList
 * @see amadeus.workspace.ToDo
 * @see amadeus.workspace.Deadline
 * @see amadeus.workspace.Event
 */
package amadeus.workspace;