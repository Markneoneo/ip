/**
 * The main class for the Amadeus chatbot interface.
 * Handles startup, shutdown messages, and user interaction.
 */


import userinterface.UserInput;

public class Amadeus
{
    private static final String DIVIDER = "=".repeat(100);
    //region Amadeus Logo
    private static final String AMADEUS =
            """
                                                                                   @
                                                                                @@@@
                                                                              @@@@@@
                                                                           @@@@@@#@@
                                                                         @@@@@@@@@@@
                                                                       @@@@@@@  @@ @
                                                                    @@@@@@@:    @@@@
                                                                  @@@@@@@       @@@@
                                                               -@@@@@@#         @@@@
                                                             @@@@@@@            @@@@
                                                           @@@@@@@              @@@@
                                                         @@@@@@=                @@@@
                                                      @@@@@@@                   @@@@
                                                   +@@@@@@=                     @@@@
                                                 @@@@@@@                        @@@@
                                               @@@@@@@                          @@@@
                                             @@@@@@#                            @@@@
                                          @@@@@@@+                              @@@@
                                       @@@@@@@#        @@@@@@@@@@@@@@@@@@@@@@@  @@@@
                                     @%#@*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@    @@@@=@@@@@@@@@@@@@@@@@#-
                                   #@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@=+         @@ @@@@@@@@@@@@@@@@%@@@@@@@@
                                 @@%@@@@                                     @@                       @@@
                              @@@@@@@@ @@@@@@@@@@@@@@@ @@@@@@@@@@@ @@@@@@@@@@@@ @@@@@@@@@@@@ @@       @@@ @@@@@@@@@@@
                            @@@#@@@    @@@   @@    @@@         @@@ @@#       @@ @@       @@@ @@       @@@ @@        \s
                         @@@@@@@@      @@@   @@    @@@ @@@@@@@@@@@ @@@       @@ @@@@@@@@@@@@ @@       @@@ @@@@@@@@@@@
                       @@@@@@@         @@@   #@    @@@ @@       @@ @@@       @@ @@           @@       @@%          @@
                     @@@@@@@@@@@@@@@@@@@@@   @@    @@@ @@@@@@@@@@@ @@@@@@@@@@@@ @@@@@@@@@@@@ @@@@@@@@@@@ @@@@@@@@@@@@
                                                   @@-                                         @@@@@@@
                                                   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                                                                                  @@@@@@@@+
                                                                                @@@@@@@@-
                                                                                @@@@@@@
                                                                                @@@@@
                                                                                @@@
                                                                                @
                    """;
    //endregion

    /**
     * Prints a string to the console with a typing effect.
     *
     * @param text The text to be printed.
     * @param duration The delay (in milliseconds) between each character.
     */
    public static void slowPrint(String text, int duration)
    {
        for (int i = 0; i < text.length(); i++)
        {
            System.out.print(text.charAt(i));
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Displays the boot sequence when Amadeus starts.
     * Shows login messages and an introduction.
     */
    public static void logIn()
    {
        System.out.println(DIVIDER);
        slowPrint("User ID: Salieri\nLogging in...\nLaunching...\n", 50);
        slowPrint(AMADEUS, 1);
        slowPrint("""
                Nice to meet you, I'm Kurisu Makise, a.k.a. Amadeus.
                I look forward to working with you.
                """,10);
        System.out.println(DIVIDER);
    }

    /**
     * Displays shutdown messages when Amadeus is exiting.
     * Prints a farewell message.
     */
    public static void logOff()
    {
        //System.out.println(DIVIDER);
        slowPrint("Goodbye. May our timelines converge once more.\nEl Psy Kongroo\n",30);
        System.out.println(DIVIDER);
    }

    public static void main(String[] args)
    {
        logIn();
        UserInput.getCommand();
        logOff();
    }
}
