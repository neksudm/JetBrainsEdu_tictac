package tictactoe;

import java.util.Random;

public class MediumAI extends Player {
    MediumAI(char role) {
        super(role);
    }

    public int[] getTurn(Field field) {
        System.out.println(GStrings.AI_MAKING_MOVE + "\"" + GStrings.MEDIUM + "\"");
        Random rnd = new Random();
        int[] turn = getWinTurn(field);
        if (turn == null) {
            int x = rnd.nextInt(3);
            int y = rnd.nextInt(3);

            while (field.getCell(x, y) != GStrings.CHAR_E) {
                x = rnd.nextInt(3);
                y = rnd.nextInt(3);
            }

            return new int[]{x, y};
        }
        return turn;
    }

    private static int getWinTurnForLine(char[] line, char role) {
        int countRole = 0;
        int countEmpty = 0;
        int winCoord = -1;
        for (int x = 0; x < 3; x++) {
            if (line[x] == role) {
                countRole++;
            }

            if (line[x] == GStrings.CHAR_E) {
                countEmpty++;
                winCoord = x;
            }
        }

        if (countRole == 2 && countEmpty == 1) {
            return winCoord;
        }
        return -1;
    }

    private int[] getWinTurn(Field field) {
        int winX = 0;
        int winY = 0;
        char ownRole = this.getRole();
        char opponentRole = (ownRole == GStrings.CHAR_X) ? GStrings.CHAR_O : GStrings.CHAR_X;

        //find own win turn
        //// in rows
        for (winX = 0; winX < 3; winX++) {
            winY = getWinTurnForLine(field.getRow(winX), ownRole);
            if (winY >= 0) {
                return new int[]{winX, winY};
            }
        }
        ////in columns
        for (winY = 0; winY < 3; winY++) {
            winX = getWinTurnForLine(field.getColumn(winY), ownRole);
            if (winX >= 0) {
                return new int[]{winX, winY};
            }
        }
        ////in diagonals
        winX = getWinTurnForLine(field.getDiagonal(0), ownRole);
        if (winX >= 0) {
            return new int[]{winX, winX};
        }

        winX = getWinTurnForLine(field.getDiagonal(1), ownRole);
        if (winX >= 0) {
            return new int[]{winX,2-winX};
        }

        //find opponent win turn
        //// in rows
        for (winX = 0; winX < 3; winX++) {
            winY = getWinTurnForLine(field.getRow(winX), opponentRole);
            if (winY >= 0) {
                return new int[]{winX, winY};
            }
        }
        ////in columns
        for (winY = 0; winY < 3; winY++) {
            winX = getWinTurnForLine(field.getColumn(winY), opponentRole);
            if (winX >= 0) {
                return new int[]{winX, winY};
            }
        }
        ////in diagonals
        winX = getWinTurnForLine(field.getDiagonal(0), opponentRole);
        if (winX >= 0) {
            return new int[]{winX, winX};
        }

        winX = getWinTurnForLine(field.getDiagonal(1), opponentRole);
        if (winX >= 0) {
            return new int[]{winX,2-winX};
        }
        return null;
    }
}
