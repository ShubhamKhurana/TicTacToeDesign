package TicTacToeCode.controller;

import TicTacToeCode.exception.GameOverException;
import TicTacToeCode.models.*;
import TicTacToeCode.service.winningStrategy.WinningStrategies;
import TicTacToeCode.service.winningStrategy.WinningStrategy;
import TicTacToeCode.service.winningStrategy.WinningStrategyFactory;

import java.util.List;

public class GameController {
    // For Frontend, Browsers or Apps are the client
    // For this application, we have Main method as our client (which sends the request)
    // Controller is the one which receives request from client
    // So, we createGame
    public Game createGame(int dimension, List<Player> players, WinningStrategies winningStrategy){
        // now we'll use Builder to build it
        // calling builder with className coz builder is static
        // Also remember build() could throw an Exception, so put try-catch
        try{
            return Game.builder()
                    .dimension(dimension)
                    .players(players)
                    .winningStrategy(WinningStrategyFactory.getWinningStrategy(winningStrategy, dimension))
                    .build();
        }
        catch(Exception e){
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("\nCould Not Start the Game! Problem with Inputs");
        }

        return null;
    }

    // Apart from this, we need to display the board again & again too
    public void displayBoard(Game game){
        game.getCurrentBoard().printBoard();
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public Player getGameWinner(Game game){
        return game.getWinner();
    }

    public Move executeMove(Game game, Player player) throws GameOverException {
        Move move = player.makeMove(game.getCurrentBoard());
        game.setNumberOfSymbols(game.getNumberOfSymbols()+1);

        updateGameStatus(game);
        updateGameMoves(game, move);
        updateBoardStates(game);

        return move;
    }

    public Player checkWinner(Game game, Move lastPlayedMove){
        Player player = game.getWinningStrategy().checkWinner(game.getCurrentBoard(), lastPlayedMove);
        if(player != null){
            game.setWinner(player);
            game.setGameStatus(GameStatus.GAME_COMPLETE);
            return player;
        }
        return null;
    }

    public void undoMove(Game game, Move move){
        // TODO: Write Undo logic here!
        game.getMoves().remove(game.getMoves().size()-1);
        game.getBoardStates().remove(game.getBoardStates().size()-1);
    }

    public void replayGame(Game game){
        // TODO: Write Replay Logic here
        for(Board board : game.getBoardStates()){
            board.printBoard();
        }
    }

    private void updateGameMoves(Game game, Move move){
        game.getMoves().add(move);
    }

    private void updateBoardStates(Game game){
        // copy constructor is required
        // else same reference se it will only have the last state of game at all values
        Board currentBoard = game.getCurrentBoard();
        game.getBoardStates().add(currentBoard);
    }

    private void updateGameStatus(Game game){
        int numberOfSymbols = game.getNumberOfSymbols();
        int dimension = game.getCurrentBoard().getSize();

        if(numberOfSymbols == (dimension*dimension)){
            game.setGameStatus(GameStatus.GAME_DRAW);
        }
    }

}
