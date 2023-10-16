package TicTacToeCode.service.botPlayingStrategy;

public class BotPlayingStrategyFactory {
    public BotPlayingStrategy getBotPlayingStrategy(){
        return new RandomBotPlayingStrategy();
    }
}
