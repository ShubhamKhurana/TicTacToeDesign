package TicTacToeCode.models;

import TicTacToeCode.exception.GameOverException;
import TicTacToeCode.service.botPlayingStrategy.BotPlayingStrategy;
import TicTacToeCode.service.botPlayingStrategy.BotPlayingStrategyFactory;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;

    public Bot(int id, String name, char symbol, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
        super(id, name, symbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    // This will override the Player's makeMove()
    public Move makeMove(Board board) throws GameOverException {
        BotPlayingStrategy botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy();
        return botPlayingStrategy.makeMove(board, this);
    }
}
