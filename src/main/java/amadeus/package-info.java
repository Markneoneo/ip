/**
 * The root package for the Amadeus chatbot application.
 * <p>
 * This package contains the main entry point for the application ({@link amadeus.Amadeus}) and
 * several sub-packages that handle different aspects of the chatbot's functionality:
 * <ul>
 *   <li>{@link amadeus.brain}: Handles error management and data persistence.</li>
 *   <li>{@link amadeus.cognition}: Manages command parsing and execution.</li>
 *   <li>{@link amadeus.perception}: Provides utilities for parsing user input, such as dates and numbers.</li>
 *   <li>{@link amadeus.personality}: Manages user interaction and speech output.</li>
 *   <li>{@link amadeus.workspace}: Manages tasks and the task list.</li>
 * </ul>
 * <p>
 * The {@link amadeus.Amadeus} class serves as the main entry point, initializing the chatbot
 * and managing the interaction loop. It coordinates the functionality of the sub-packages to
 * provide a seamless user experience.
 * </p>
 *
 * <h2>Usage Example</h2>
 * <pre>
 * // Start the Amadeus chatbot
 * Amadeus.awaken();
 * </pre>
 *
 * @see amadeus.Amadeus
 * @see amadeus.brain
 * @see amadeus.cognition
 * @see amadeus.perception
 * @see amadeus.personality
 * @see amadeus.workspace
 */
package amadeus;