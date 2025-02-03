/**
 * A utility class for converting written number words into their numeric equivalents.
 * This implementation follows a rule-based approach without relying on a hash map
 * to allow flexibility and expansion.
 */

package userinterface;
import java.util.*;

/**
 * Implements rule-based number conversion from words to integers.
 * Handles numbers up to the thousands.
 * Supports numbers with hyphens (e.g., "twenty-one") by replacing hyphens with spaces.
 * Ignores the word "and" for better parsing.
 * Ensures incorrect inputs return -1 as an error indicator.
 */

public class NumberConverter
{
    /**
     * Mapping of unit numbers (one to nineteen) to their integer values.
     */
    private static final Map<String, Integer> UNITS = Map.ofEntries(
            Map.entry("one", 1), Map.entry("two", 2), Map.entry("three", 3),
            Map.entry("four", 4), Map.entry("five", 5), Map.entry("six", 6),
            Map.entry("seven", 7), Map.entry("eight", 8), Map.entry("nine", 9),
            Map.entry("ten", 10), Map.entry("eleven", 11), Map.entry("twelve", 12),
            Map.entry("thirteen", 13), Map.entry("fourteen", 14), Map.entry("fifteen", 15),
            Map.entry("sixteen", 16), Map.entry("seventeen", 17), Map.entry("eighteen", 18),
            Map.entry("nineteen", 19)
    );

    /**
     * Mapping of tens multiples (twenty to ninety) to their integer values.
     */
    private static final Map<String, Integer> TENS = Map.ofEntries(
            Map.entry("twenty", 20), Map.entry("thirty", 30), Map.entry("forty", 40),
            Map.entry("fifty", 50), Map.entry("sixty", 60), Map.entry("seventy", 70),
            Map.entry("eighty", 80), Map.entry("ninety", 90)
    );

    /**
     * Mapping of large number multipliers (hundred, thousand) to their values.
     */
    private static final Map<String, Integer> MULTIPLIERS = Map.ofEntries(
            Map.entry("hundred", 100), Map.entry("thousand", 1000)
    );

    /**
     * Converts a number written in words into its integer representation.
     * Supports numbers from "one" to "nine thousand nine hundred ninety-nine."
     * Handles compound numbers like "twenty-one" or "three hundred and five."
     * Ignores the word "and" for natural language compatibility.
     * Returns -1 if input is invalid or contains unrecognized words.
     *
     * @param input The number written in words (e.g., "two hundred forty-five").
     * @return The integer representation of the input word, or -1 for invalid input.
     */
    public static int wordToNumber(String input)
    {
        if (input == null || input.trim().isEmpty()) { return -1; } // Invalid Input

        // Splits the input into an array of words using spaces/tabs/newlines as delimiters
        String[] words = input.toLowerCase().replaceAll("-", " ").split("\\s+");
        int result = 0; // Final number
        int current = 0; // Value of the current segment

        for (String word : words)
        {
            if (UNITS.containsKey(word))
            {
                current += UNITS.get(word);
            }
            else if (TENS.containsKey(word))
            {
                current += TENS.get(word);
            }
            else if (MULTIPLIERS.containsKey(word))
            {
                int multiplier = MULTIPLIERS.get(word);

                if (multiplier == 100)
                {
                    current *= multiplier; // Handle "hundred"
                } else {
                    current *= multiplier; // Handle "thousand"
                    result += current; // Add to the final result
                    current = 0; // Reset for the next part
                }
            }
            else if (!word.equals("and"))
            {
                return -1; // Invalid word
            }
        }
        return result + current;
    }

    public static void main(String[] args) {
        // Test Cases
        System.out.println(wordToNumber("two hundred and forty-five")); // 245
        System.out.println(wordToNumber("three thousand one hundred")); // 3100
        System.out.println(wordToNumber("nine hundred ninety-nine")); // 999
    }
}
