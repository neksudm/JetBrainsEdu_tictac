package tictactoe;

abstract class Player {
    private final char role;

    public Player(char role) {
        this.role = role;
    }

    public char getRole() {
        return this.role;
    }

    abstract int[] getTurn(Field field);

    public static Player createPlayer(String mode, char role) {
        switch (mode) {
            case GStrings.USER: {
                return new User(role);
            }
            case GStrings.EASY: {
                return new EasyAI(role);
            }
            case GStrings.MEDIUM: {
                return new MediumAI(role);
            }
            case GStrings.HARD: {
                return new HardAI(role);
            }
        }
        return null;
    }
}
