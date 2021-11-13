package tictactoe;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public interface Input {
    final static Scanner sc = new Scanner(System.in);
    final static List<String> PlayerModes = Arrays.asList(GStrings.EASY, GStrings.MEDIUM, GStrings.HARD, GStrings.USER);

    static String[] command() {
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
                    if (input.length != 1) {
                        break;
                    } else return input;
                }
                case "start": {
                    if ((input.length != 3)) {
                        break;
                    } else {
                        if (PlayerModes.contains(input[1]) && PlayerModes.contains(input[2])) {
                            return input;
                        }
                    }
                }
            }
            System.out.println(GStrings.ERROR_BAD_PARAMETERS);
        }
    }

    static int[] coordinates() {
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
                    return new int[] {x-1, y-1};
                }
            }
            System.out.println(GStrings.ERROR_COORDINATES_FORMAT);
        }
    }
}
