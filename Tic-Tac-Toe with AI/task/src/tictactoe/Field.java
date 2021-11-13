package tictactoe;

import java.util.ArrayList;

class Field {
    private final char[][] field;

    Field() {
        char EMPTY = GStrings.CHAR_E;
        this.field = new char[][]{
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY}
        };
    }

    public char[][] getField() {
        return this.field;
    }

    public ArrayList<String> getLines() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            list.add(String.valueOf(getRow(i)));
            list.add(String.valueOf(getColumn(i)));
        }
        list.add(String.valueOf(getDiagonal(0)));
        list.add(String.valueOf(getDiagonal(1)));
        return list;
    }

    public void setCell(int[] coords, char character) {
        int x = coords[0];
        int y = coords[1];
        this.field[x][y] = character;
    }

    public char getCell(int x, int y) {
        return this.field[x][y];
    }

    public char[] getRow(int row) {
        return this.field[row];
    }

    public char[] getColumn(int col) {
        char[] column = new char[3];

        for (int i = 0; i < 3; i++) {
            column[i] = this.field[i][col];
        }
        return column;
    }

    public char[] getDiagonal(int diagonalIndex) {
        char[] diagonal = new char[3];
        switch (diagonalIndex) {
            case 0: {
                for (int i = 0; i < 3; i++) {
                    diagonal[i] = this.field[i][i];
                }
                break;
            }
            case 1: {
                for (int i = 2; i >= 0; i--) {
                    diagonal[i] = this.field[i][2-i];
                }
                break;
            }
        }
        return diagonal;
    }
}
