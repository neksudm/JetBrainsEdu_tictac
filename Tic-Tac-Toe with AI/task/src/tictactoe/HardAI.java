package tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class HardAI extends Player {
    HardAI(char role) {
        super(role);
    }

    public int[] getTurn(Field field) {
        System.out.println(GStrings.AI_MAKING_MOVE + "\"" + GStrings.HARD + "\"");
        
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = field.getCell(i,j);
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                // Check if cell is empty
                if (board[i][j] == GStrings.CHAR_E)
                {
                    // Make the move
                    board[i][j] = this.getRole();

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, 0, false);

                    // Undo the move
                    board[i][j] = GStrings.CHAR_E;

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal)
                    {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        return new int[] {bestMove.row, bestMove.col};
    }

    static Boolean isMovesLeft(char board[][]){
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == GStrings.CHAR_E)
                    return true;
        return false;
    }

    private int minimax(char board[][],
                       int depth, Boolean isMax)
    {
        int score = evaluate(board);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (!isMovesLeft(board))
            return 0;

        // If this maximizer's move
        if (isMax)
        {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j] == GStrings.CHAR_E)
                    {
                        // Make the move
                        board[i][j] = this.getRole();

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = GStrings.CHAR_E;
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else
        {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j] == GStrings.CHAR_E)
                    {
                        // Make the move
                        board[i][j] = this.opponentRole();

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = GStrings.CHAR_E;
                    }
                }
            }
            return best;
        }
    }

    private int evaluate(char b[][])
    {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++)
        {
            if (b[row][0] == b[row][1] &&
                    b[row][1] == b[row][2])
            {
                if (b[row][0] == this.getRole())
                    return +10;
                else if (b[row][0] == this.opponentRole())
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++)
        {
            if (b[0][col] == b[1][col] &&
                    b[1][col] == b[2][col])
            {
                if (b[0][col] == this.getRole())
                    return +10;

                else if (b[0][col] == this.opponentRole())
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2])
        {
            if (b[0][0] == this.getRole())
                return +10;
            else if (b[0][0] == this.opponentRole())
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0])
        {
            if (b[0][2] == this.getRole())
                return +10;
            else if (b[0][2] == this.opponentRole())
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    private char opponentRole() {
        if (this.getRole() == GStrings.CHAR_X) return GStrings.CHAR_O;
        else return GStrings.CHAR_X;
    }
}
