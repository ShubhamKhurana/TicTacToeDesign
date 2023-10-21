package TicTacToeCode.models;

import TicTacToeCode.exception.GameOverException;

import java.util.Scanner;

public class Player {
    private int id;
    private String name;
    private char symbol;
    private PlayerType playerType;

    public Player(){

    }

    public Player(int id, String name, char symbol, PlayerType playerType) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public Move makeMove(Board board) throws GameOverException {
        Scanner sc = new Scanner(System.in);
        System.out.println(this.name + "'s Turn (" + this.getPlayerType() + ")");
        System.out.println("Enter row for your move: ");
        int row = sc.nextInt();
        System.out.println("Enter column for your move: ");
        int col = sc.nextInt();
        // TODO: validation for the move, check row and column, and CellStatus

        if(row < 0 || col < 0 || row >= board.getSize() || col >= board.getSize()){
            System.out.println("Invalid Input");
            return null;
        }

        if(board.getBoard().get(row).get(col).getCellState().equals(CellState.FILLED)){
            return null;
        }

        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(this);

        return new Move(row, col, this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
