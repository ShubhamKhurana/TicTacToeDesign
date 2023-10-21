package TicTacToeCode.models;

import TicTacToeCode.exception.DuplicateSymbolException;
import TicTacToeCode.exception.InvalidBoardSizeException;
import TicTacToeCode.exception.InvalidBotCountException;
import TicTacToeCode.exception.InvalidNumberOfPlayersException;
import TicTacToeCode.service.winningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// Let's Add Builder Design Pattern for the Game class
public class Game {
    private Board currentBoard;
    private List<Player> players;
    private Player currentPlayer;
    private GameStatus gameStatus;
    private Player winner;
    private List<Move> moves;
    private List<Board> boardStates;
    private WinningStrategy winningStrategy;
    private int numberOfSymbols;

    private Game(Board currentBoard, List<Player> players, WinningStrategy winningStrategy){
        this.currentBoard = currentBoard;
        this.players = players;
        this.gameStatus = GameStatus.GAME_IN_PROGRESS;
        this.moves = new ArrayList<Move>();
        this.boardStates = new ArrayList<Board>();
        this.winningStrategy = winningStrategy;
        this.numberOfSymbols = 0;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        // in Game constructor,
        // we're passing 3 arguments that we use to build an object, and initialise it
        // those values should be validated, before they're passed
        // because as soon as we enter the constructor the object is created
        // even before it's first line is executed
        // so, we'll add those 3 arguments for this Builder class

        // we can take board, or boardSize since it's the size we validate here
        // private Board currentBoard;
        private int dimension;
        private List<Player> players;
        private WinningStrategy winningStrategy;

        // Now we'll write setters for these 3 properties
        // If public setters for any value is not provided, then that property is basically immutable
        // Also make return type of all these setters as Builder for chaining while initialising
        // Also change the setProperty() method to property(), remove "set" from method here

        public Builder dimension(int dimension){
            this.dimension = dimension;
            return this;
        }

        public Builder players(List<Player> players){
            this.players = players;
            return this;
        }

        public Builder winningStrategy(WinningStrategy winningStrategy){
            this.winningStrategy = winningStrategy;
            return this;
        }

        // Now, we start with validations
        // remember to throw exceptions in declaration
        // when you define a function that throws exception
        public void validateBotCount() throws InvalidBotCountException{
            // remember to add getter setters for the property we're checking
            int botCount = 0;
            for(Player player : players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }

            if(botCount > 1){
                throw new InvalidBotCountException("\nBot Count can't be more than 1. Currently, botCount is " + botCount);
            }
        }

        public void validateBoardSize() throws InvalidBoardSizeException {
            if(dimension < 3 || dimension > 10){
                throw new InvalidBoardSizeException("\nBoard size should be >= 3 and <= 10. Currently, boardSize is " + dimension);
            }
        }

        public void validatePlayerNumber() throws InvalidNumberOfPlayersException {
            if(players.size() != dimension-1){
                throw new InvalidNumberOfPlayersException("\nNumber of Players is Invalid. Currently, Number of Players is " + players.size());
            }
        }

        // each player should have unique symbol
        public void validateDuplicateSymbol() throws DuplicateSymbolException{
            HashSet<Character> symbolSet = new HashSet<>();
            // new entry does not get added in symbolSet on adding duplicate entry
            for(Player player : players){
                symbolSet.add(player.getSymbol());
            }

            if(symbolSet.size() != players.size()){
                throw new DuplicateSymbolException("All Players must have unique symbols");
            }
        }

        // Now call all these validate functions
        // remember to add throw Exception for this
        public void validate() throws InvalidBotCountException, InvalidBoardSizeException, InvalidNumberOfPlayersException, DuplicateSymbolException{
            validateBotCount();
            validateBoardSize();
            validatePlayerNumber();
            validateDuplicateSymbol();
        }

        // now to build the object,
        // call validate() first, then return the object
        // so Game object will only be created when all conditions are passed

        public Game build() throws InvalidBotCountException, InvalidBoardSizeException, InvalidNumberOfPlayersException, DuplicateSymbolException{
            validate();
            return new Game(new Board(dimension), players, winningStrategy);
        }
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public List<Board> getBoardStates() {
        return boardStates;
    }

    public WinningStrategy getWinningStrategy() {
        return winningStrategy;
    }

    public int getNumberOfSymbols() {
        return numberOfSymbols;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setNumberOfSymbols(int numberOfSymbols) {
        this.numberOfSymbols = numberOfSymbols;
    }
}
