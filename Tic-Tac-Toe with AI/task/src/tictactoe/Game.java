package tictactoe;

public class Game {

    public enum GameState {
        IN_PROGRESS,
        FINISHED
    }

    private GameState state;
    private Player activePlayer;
    final Player playerX;
    final Player playerO;
    final Field field;

    public Game(String[] players) {
        playerX = Player.createPlayer(players[1], GStrings.CHAR_X);
        playerO = Player.createPlayer(players[2], GStrings.CHAR_O);
        activePlayer = playerX;
        field = new Field();
        state = GameState.IN_PROGRESS;
    }

    private void drawField(Field field) {
        System.out.println("---------");
        for (char[] chars : field.getField()) {
            System.out.print("| ");
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public String testWinLine(String line) {
        if (line.equals(GStrings.XXX)) {
            return GStrings.X_WINS;
        } else if (line.equals(GStrings.OOO)) {
            return GStrings.O_WINS;
        }
        return null;
    }

    public String checkFieldState(Field field) {
        for (String line : field.getLines()) {
            String winString = testWinLine(line);
            if (winString != null) {
                return winString;
            }
        }

        for (char[] chars : field.getField()) {
            for (char aChar : chars) {
                if (aChar == GStrings.CHAR_E) {
                    return null;
                }
            }
        }
        return GStrings.DRAW;
    }

    public void startGame() {
        drawField(field);
        while (this.state != GameState.FINISHED) {
            field.setCell(activePlayer.getTurn(field), activePlayer.getRole());
            drawField(field);

            String fieldState = checkFieldState(field);
            if (fieldState != null) {
                System.out.println(fieldState);
                System.out.println();
                state = GameState.FINISHED;
            } else {
                activePlayer = (activePlayer == playerX) ? playerO : playerX;
            }
        }
    }
}
