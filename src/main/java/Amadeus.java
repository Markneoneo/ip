import java.util.Scanner; // Methods for input

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

    public static void logIn()
    {
        System.out.println(DIVIDER);
        System.out.println("User ID: Salieri\nLogging in...\nLaunching...\n");
        System.out.println(AMADEUS);
        System.out.println("Nice to meet you, I'm Kurisu Makise, a.k.a. Amadeus.");
        System.out.println("I look forward to working with you.");
        System.out.println(DIVIDER);
    }

    public static void logOff()
    {
        //System.out.println(DIVIDER);
        System.out.println("Goodbye. May our timelines converge once more.\nEl Psy Kongroo");
        System.out.println(DIVIDER);
    }

    public static void echo()
    {
        String input;
        Scanner in = new Scanner(System.in);
        do
        {
            input = in.nextLine(); // User Input
            System.out.println(DIVIDER);
            System.out.println(input);
            System.out.println(DIVIDER);
        } while(!(input.equals("bye")));
        // Log Off at "bye"
        logOff();
    }

    public static void main(String[] args)
    {
        logIn();
        echo();
    }
}
