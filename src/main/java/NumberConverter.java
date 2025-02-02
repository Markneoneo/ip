/**
 * Class to recognise unique number words
 */

import java.util.*;

/**
 * Rule-Based Number Conversion (No Hash Map)
 * Breaks down words into meaningful parts
 * Supports expansion without manually adding numbers
 * Cannot use Map.of(...) because Java has a limit of 10 key-value pairs
 */
public class NumberConverter
{
    private static final Map<String, Integer> UNITS = Map.ofEntries(
            Map.entry("one", 1), Map.entry("two", 2), Map.entry("three", 3),
            Map.entry("four", 4), Map.entry("five", 5), Map.entry("six", 6),
            Map.entry("seven", 7), Map.entry("eight", 8), Map.entry("nine", 9),
            Map.entry("ten", 10), Map.entry("eleven", 11), Map.entry("twelve", 12),
            Map.entry("thirteen", 13), Map.entry("fourteen", 14), Map.entry("fifteen", 15),
            Map.entry("sixteen", 16), Map.entry("seventeen", 17), Map.entry("eighteen", 18),
            Map.entry("nineteen", 19)
    );

    private static final Map<String, Integer> TENS = Map.ofEntries(
            Map.entry("twenty", 20), Map.entry("thirty", 30), Map.entry("forty", 40),
            Map.entry("fifty", 50), Map.entry("sixty", 60), Map.entry("seventy", 70),
            Map.entry("eighty", 80), Map.entry("ninety", 90)
    );

    // I assume millions are not needed
    private static final Map<String, Integer> MULTIPLIERS = Map.ofEntries(
            Map.entry("hundred", 100), Map.entry("thousand", 1000)
    );

    /**
     * Converts a given number word to an integer
     * @param input String of the word to be converted into a number
     * @return Integer output of the written number string
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

    public static void main(String[] args)
    {
        // Test Cases
        System.out.println(wordToNumber("two hundred and forty-five")); // 245
        System.out.println(wordToNumber("three thousand one hundred")); // 3100
        System.out.println(wordToNumber("nine hundred ninety-nine")); // 999
    }
}
