package TicTacToeCode.service.botPlayingStrategy;

import TicTacToeCode.exception.GameOverException;
import TicTacToeCode.models.Board;
import TicTacToeCode.models.Move;
import TicTacToeCode.models.Player;

public interface BotPlayingStrategy {
    Move makeMove(Board board, Player player) throws GameOverException;
}
