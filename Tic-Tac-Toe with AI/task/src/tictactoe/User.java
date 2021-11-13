package tictactoe;

public class User extends Player implements Input {
    User(char role) {
        super(role);
    }
    public int[] getTurn(Field field) {
        int[] input = Input.coordinates();
        while (field.getCell(input[0],input[1]) != GStrings.CHAR_E) {
            System.out.println(GStrings.ERROR_COORDINATES_OCCUPIED);
            input = Input.coordinates();
        }
        return input;
    }
}
