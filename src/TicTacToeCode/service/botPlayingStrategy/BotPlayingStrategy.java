package TicTacToeCode.service.botPlayingStrategy;

import TicTacToeCode.exception.GameOverException;
import TicTacToeCode.models.Board;
import TicTacToeCode.models.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board) throws GameOverException;
}
