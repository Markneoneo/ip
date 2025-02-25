package amadeus.perception;
import amadeus.brain.AmadeusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateConverter
{
    // Supported date formats
    private static final List<DateTimeFormatter> DATE_FORMATS = Arrays.asList(
            DateTimeFormatter.ofPattern("d/M/yyyy"), // e.g., 2/12/2019
            DateTimeFormatter.ofPattern("d-M-yyyy"), // e.g., 2-12-2019
            DateTimeFormatter.ofPattern("d M yyyy")  // e.g., 2 12 2019
    );

    // Supported date-time formats
    private static final List<DateTimeFormatter> DATE_TIME_FORMATS = Arrays.asList(
            DateTimeFormatter.ofPattern("d/M/yyyy ha"),    // e.g., 2/12/2019 6PM
            DateTimeFormatter.ofPattern("d/M/yyyy h:mma"), // e.g., 2/12/2019 6:00PM
            DateTimeFormatter.ofPattern("d/M/yyyy h.mma"), // e.g., 2/12/2019 6.30AM
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),  // e.g., 2/12/2019 1800
            DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"), // e.g., 2/12/2019 18:00
            DateTimeFormatter.ofPattern("d/M/yyyy HH.mm"), // e.g., 2/12/2019 18.00
            DateTimeFormatter.ofPattern("d-M-yyyy ha"),    // e.g., 2-12-2019 6PM
            DateTimeFormatter.ofPattern("d-M-yyyy h:mma"), // e.g., 2-12-2019 6:00PM
            DateTimeFormatter.ofPattern("d-M-yyyy h.mma"), // e.g., 2-12-2019 6.30AM
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),  // e.g., 2-12-2019 1800
            DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"), // e.g., 2-12-2019 18:00
            DateTimeFormatter.ofPattern("d-M-yyyy HH.mm"), // e.g., 2-12-2019 18.00
            DateTimeFormatter.ofPattern("d M yyyy ha"),    // e.g., 2 12 2019 6PM
            DateTimeFormatter.ofPattern("d M yyyy h:mma"), // e.g., 2 12 2019 6:00PM
            DateTimeFormatter.ofPattern("d M yyyy h.mma"), // e.g., 2 12 2019 6.30AM
            DateTimeFormatter.ofPattern("d M yyyy HHmm"),  // e.g., 2 12 2019 1800
            DateTimeFormatter.ofPattern("d M yyyy HH:mm"), // e.g., 2 12 2019 18:00
            DateTimeFormatter.ofPattern("d M yyyy HH.mm")  // e.g., 2 12 2019 18.00
    );

    // Output format for dates
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy");

    // Output format for date-times
    private static final DateTimeFormatter OUTPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy, h:mma");


    /**
     * Parses a date string into a LocalDate or LocalDateTime object.
     * Supports multiple date and time formats.
     *
     * @param dateString The date string to parse.
     * @return A LocalDate or LocalDateTime object, or null if the input is invalid.
     */
    public static Object parseDate(String dateString) throws AmadeusException
    {
        // Normalize AM/PM to be case-insensitive
        dateString = dateString.replaceAll("(?i)am", "AM").replaceAll("(?i)pm", "PM");

        // Try parsing as a date-time first
        for (DateTimeFormatter format : DATE_TIME_FORMATS)
        {
            try {
                return LocalDateTime.parse(dateString, format);

            } catch (DateTimeParseException e) {
                // Ignore and try the next format
            }
        }

        // Try parsing as a date if date-time parsing fails
        for (DateTimeFormatter format : DATE_FORMATS)
        {
            try {
                return LocalDate.parse(dateString, format);

            } catch (DateTimeParseException e) {
                // Ignore and try the next format
            }
        }

        // If no format matches, throw an exception
        throw AmadeusException.invalidDate();
    }


    /**
     * Formats a date or date-time object into a user-friendly string.
     *
     * @param date The date or date-time object to format.
     * @return The formatted date string (e.g., "Oct 15 2019" or "Oct 15 2019, 6:00PM").
     */
    public static String formatDate(Object date)
    {
        if (date instanceof LocalDateTime) {
            return ((LocalDateTime) date).format(OUTPUT_DATE_TIME_FORMAT);

        } else if (date instanceof LocalDate) {
            return ((LocalDate) date).format(OUTPUT_DATE_FORMAT);

        } else {
            return "⚠️ Invalid Date";
        }
    }


    public static void main(String[] args)
    {
        //create dates from strings
        LocalDate d1 = LocalDate.parse("2019-12-01");
        LocalDate d2 = LocalDate.parse("2019-12-02");
        LocalDate d3 = LocalDate.parse("2019-12-02");

        //compare dates
        System.out.println(d1.isBefore(d2)); // -> true
        System.out.println(d1.isAfter(d2)); // -> false
        System.out.println(d2.equals(d3)); // -> true

        //work with dates
        System.out.println(d1.getDayOfWeek()); // -> SUNDAY
        System.out.println(d1.getMonth()); // -> DECEMBER
        System.out.println(d1.plusYears(1));  // -> 2020-12-01

        // get today's date and print it in a specific format
        LocalDate d4 = LocalDate.now();
        System.out.println(d4); // -> 2019-10-15
        System.out.println(d4.format(DateTimeFormatter.ofPattern("MMM d yyyy"))); // -> Oct 15 2019
    }
}