package TicTacToeCode.service.botPlayingStrategy;

import TicTacToeCode.exception.GameOverException;
import TicTacToeCode.models.*;

import java.util.List;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{
    public Move makeMove(Board board, Player player) throws GameOverException {
        // fill up first empty cell found
        List<List<Cell>> matrix = board.getBoard();
        for(int i=0;i<matrix.size();i++){
            for(int j=0;j<matrix.size();j++){
                if(matrix.get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    matrix.get(i).get(j).setPlayer(player);
                    matrix.get(i).get(j).setCellState(CellState.FILLED);
                    return new Move(i, j, player);
                }
            }
        }

        // there's nothing to play
        throw new GameOverException("No new cells to play with : GAME OVER!");
    }
}
