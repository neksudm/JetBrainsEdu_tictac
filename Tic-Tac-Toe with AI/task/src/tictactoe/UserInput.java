package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class UserInput {
    private final static Scanner sc = new Scanner(System.in);
    private final static String[] PlayerModes = {
            GStrings.EASY, GStrings.MEDIUM, GStrings.HARD, GStrings.USER
    };

    public static String[] processCommand() {
        String[] input;
        while (true) {
            System.out.print(GStrings.INPUT_COMMAND);
            input = sc.nextLine().split(" ");

            if (input[0] == null) {
                System.out.println(GStrings.ERROR_BAD_PARAMETERS);
                continue;
            }

            switch (input[0]) {
                case "exit": {
                    System.exit(0);
                }
                case "start": {
                    if ((input.length != 3)) {
                        break;
                    } else {
                        if (Arrays.asList(PlayerModes).contains(input[1]) && Arrays.asList(PlayerModes).contains(input[2])) {
                            return input;
                        }
                    }
                }
            }
            System.out.println(GStrings.ERROR_BAD_PARAMETERS);
        }
    }

    public static int[] processCoordinates() {
        String[] input;
        while (true) {
            System.out.print(GStrings.INPUT_COORDINATES);
            input = sc.nextLine().split(" ");

            if (input.length == 2 && input[0].matches("\\d") && input[1].matches("\\d")) {
                int x = Integer.parseInt(input[0]);
                int y = Integer.parseInt(input[1]);

                if (x > 3 || x < 1 || y > 3 || y < 1) {
                    System.out.println(GStrings.ERROR_COORDINATES_OUT_OF_BOUND);
                    continue;
                } else {
                    return convertMathCoordsToIndex(x,y);
                }
            }
            System.out.println(GStrings.ERROR_COORDINATES_FORMAT);
        }
    }

    public static int[] convertMathCoordsToIndex(int x, int y) {
        return new int[]{3-y,x-1};
    }
}
