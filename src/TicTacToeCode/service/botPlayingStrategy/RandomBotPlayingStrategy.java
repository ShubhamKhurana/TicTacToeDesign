package TicTacToeCode.service.botPlayingStrategy;

import TicTacToeCode.exception.GameOverException;
import TicTacToeCode.models.Board;
import TicTacToeCode.models.Cell;
import TicTacToeCode.models.CellState;
import TicTacToeCode.models.Move;

import java.util.List;

public class RandomBotPlayingStrategy implements BotPlayingStrategy{
    public Move makeMove(Board board) throws GameOverException {
        // fill up first empty cell found
        List<List<Cell>> matrix = board.getBoard();
        for(int i=0;i<matrix.size();i++){
            for(int j=0;j<matrix.size();j++){
                if(matrix.get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    return new Move(i, j);
                }
            }
        }

        // there's nothing to play
        throw new GameOverException("No new cells to play with : GAME OVER!");
    }
}
