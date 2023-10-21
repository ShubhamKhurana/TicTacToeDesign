package TicTacToeCode.service.botPlayingStrategy;

public class BotPlayingStrategyFactory {
    // we can switch case syntax here when we include more strategies
    public static BotPlayingStrategy getBotPlayingStrategy(){
        return new RandomBotPlayingStrategy();
    }
}
