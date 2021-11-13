package tictactoe;

public class Main {
    public static void main(String[] args) {
        String[] input = Input.command();
        while ( !input[0].equals("exit") ) {
            Game game = new Game(input);
            game.startGame();
            input = Input.command();
        }
        System.exit(0);
    }
}
