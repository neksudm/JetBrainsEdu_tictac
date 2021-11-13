package tictactoe;

import java.util.Random;

public class EasyAI extends Player {
    EasyAI(char role) {
        super(role);
    }

    public int[] getTurn(Field field) {
        System.out.println(GStrings.AI_MAKING_MOVE + "\"" + GStrings.EASY + "\"");
        Random rnd = new Random();

        int x = rnd.nextInt(3);
        int y = rnd.nextInt(3);

        while (field.getCell(x, y) != GStrings.CHAR_E) {
            x = rnd.nextInt(3);
            y = rnd.nextInt(3);
        }
        return new int[] {x, y};
    }
}
