/**
 * Main Class for Amadeus Chatbot Interface
 */

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
     * Provides typing text visual effect to printing string
     * @param text String to be printed
     * @param duration Time paused between each symbol
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
     * Booting up messages shown when Amadeus awakens
     */
    public static void logIn()
    {
        System.out.println(DIVIDER);
        slowPrint("User ID: Salieri\nLogging in...\nLaunching...\n", 50);
        slowPrint(AMADEUS, 1);
        slowPrint("Nice to meet you, I'm Kurisu Makise, a.k.a. Amadeus.\n",10);
        slowPrint("I look forward to working with you.\n",10);
        System.out.println(DIVIDER);
    }

    /**
     * Shutdown messages shown when Amadeus sleeps
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
