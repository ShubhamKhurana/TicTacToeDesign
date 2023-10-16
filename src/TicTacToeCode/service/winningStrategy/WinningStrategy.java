package TicTacToeCode.service.winningStrategy;

import TicTacToeCode.models.Board;
import TicTacToeCode.models.Move;
import TicTacToeCode.models.Player;

public interface WinningStrategy {
    public Player checkWinner(Board board, Move lastMove);
}
