/**
 * Provides utilities for parsing and converting user input into executable commands and structured data.
 * <p>
 * This package includes classes that handle the interpretation of user input, such as commands, dates, and numbers.
 * These utilities are essential for translating natural language input into actionable tasks and structured data
 * that the application can process.
 * </p>
 *
 * <h2>Key Classes</h2>
 * <ul>
 *   <li>{@link amadeus.perception.Parser}: Parses user input into executable {@link amadeus.cognition.Command} objects.</li>
 *   <li>{@link amadeus.perception.DateConverter}: Converts date and time strings into {@link java.time.LocalDate} or {@link java.time.LocalDateTime} objects.</li>
 *   <li>{@link amadeus.perception.NumberConverter}: Converts number words (e.g., "twenty-one") into their numeric equivalents.</li>
 * </ul>
 *
 * <h2>Usage Example</h2>
 * <pre>
 * // Parse user input into a command
 * Command command = Parser.parse("todo Buy groceries");
 * command.execute();
 *
 * // Convert a date string into a LocalDate object
 * Object date = DateConverter.parseDate("2/12/2019");
 *
 * // Convert a number word into an integer
 * int number = NumberConverter.wordToNumber("twenty-one");
 * </pre>
 *
 * @see amadeus.cognition.Command
 * @see java.time.LocalDate
 * @see java.time.LocalDateTime
 */
package amadeus.perception;