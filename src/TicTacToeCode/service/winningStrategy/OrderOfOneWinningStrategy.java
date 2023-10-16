package TicTacToeCode.service.winningStrategy;

import TicTacToeCode.models.Board;
import TicTacToeCode.models.Move;
import TicTacToeCode.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOfOneWinningStrategy implements WinningStrategy{
    private int dimension;
    private List < HashMap<Character, Integer> > rowHashMaps;
    private List < HashMap<Character, Integer> > colHashMaps;
    private HashMap <Character, Integer> topLeftHashMap;
    private HashMap <Character, Integer> topRightHashMap;
    private HashMap <Character, Integer> cornerHashMap;

    public OrderOfOneWinningStrategy(int dimension) {
        this.dimension = dimension;
        topLeftHashMap = new HashMap<>();
        topRightHashMap = new HashMap<>();
        cornerHashMap = new HashMap<>();

        rowHashMaps = new ArrayList<>();
        colHashMaps = new ArrayList<>();

        for(int i=0;i<dimension;i++){
            rowHashMaps.add(new HashMap<>());
            colHashMaps.add(new HashMap<>());
        }
    }

    public boolean isTopLeftDiagonalCell(int row, int col){
        return (row == col);
    }

    public boolean isTopRightDiagonalCell(int row, int col){
        return (row+col == dimension);
    }

    public boolean isCornerCell(int row, int col){
        // [0,0] or [0,dimension-1] or [dimension-1,0] or [dimension-1,dimesion-1]
        if(row == 0 || row == dimension-1) return true;
        if(col == 0 || col == dimension-1) return true;
        return false;
    }

    public boolean checkRowWin(int row, char symbol){
        // if symbol does not exist, insert in map
        if(rowHashMaps.get(row).containsKey(symbol)){
            rowHashMaps.get(row).put(symbol, 0);
        }

        // for every insertion of symbol, update the count
        rowHashMaps.get(row).put(
                symbol,
                rowHashMaps.get(row).get(symbol)+1
        );

        if(rowHashMaps.get(row).get(symbol) == dimension) return true;

        return false;
    }

    public boolean checkColumnWin(int col, char symbol){
        // if symbol does not exist, insert in map
        if(colHashMaps.get(col).containsKey(symbol)){
            colHashMaps.get(col).put(symbol, 0);
        }

        // for every insertion of symbol, update the count
        colHashMaps.get(col).put(
                symbol,
                rowHashMaps.get(col).get(symbol)+1
        );

        if(colHashMaps.get(col).get(symbol) == dimension) return true;

        return false;
    }

    public boolean checkTopLeftDiagonalWin(char symbol){
        // if symbol does not exist, insert in map
        if(topLeftHashMap.containsKey(symbol)){
            topLeftHashMap.put(symbol, 0);
        }

        // for every insertion of symbol, update the count
        topLeftHashMap.put(
                symbol,
                topLeftHashMap.get(symbol)+1
        );

        if(topLeftHashMap.get(symbol) == dimension) return true;

        return false;
    }

    public boolean checkTopRightDiagonalWin(char symbol){
        // if symbol does not exist, insert in map
        if(topRightHashMap.containsKey(symbol)){
            topRightHashMap.put(symbol, 0);
        }

        // for every insertion of symbol, update the count
        topRightHashMap.put(
                symbol,
                topRightHashMap.get(symbol)+1
        );

        if(topRightHashMap.get(symbol) == dimension) return true;

        return false;
    }

    public boolean checkCornerWin(char symbol){
        // if symbol does not exist, insert in map
        if(cornerHashMap.containsKey(symbol)){
            cornerHashMap.put(symbol, 0);
        }

        // for every insertion of symbol, update the count
        cornerHashMap.put(
                symbol,
                cornerHashMap.get(symbol)+1
        );

        if(cornerHashMap.get(symbol) == 4) return true;

        return false;
    }

    public Player checkWinner(Board board, Move lastMove){
        Player player = lastMove.getPlayer();
        char symbol = player.getSymbol();

        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getColumn();

        if(checkRowWin(row, symbol)){
            return player;
        }

        if(checkColumnWin(col, symbol)){
            return player;
        }

        if(isTopLeftDiagonalCell(row, col) && checkTopLeftDiagonalWin(symbol)){
            return player;
        }

        if(isTopRightDiagonalCell(row, col) && checkTopRightDiagonalWin(symbol)){
            return player;
        }

        if(isCornerCell(row, col) && checkCornerWin(symbol)){
            return player;
        }

        return null;
    }
}
