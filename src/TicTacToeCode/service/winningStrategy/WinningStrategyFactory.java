package TicTacToeCode.service.winningStrategy;

public class WinningStrategyFactory {
    public static WinningStrategy getWinningStrategy(WinningStrategies winningStrategy, int dimension){
        // TODO: add a switch case basis of the strategy chosen, and return the object
        // DONE
        return switch (winningStrategy){
            case ORDERONE_WINNINGSTRATEGY -> new OrderOfOneWinningStrategy(dimension);
        };
    }
}
