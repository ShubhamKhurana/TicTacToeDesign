package TicTacToeCode;

import TicTacToeCode.controller.GameController;
import TicTacToeCode.exception.GameOverException;
import TicTacToeCode.models.*;
import TicTacToeCode.service.winningStrategy.WinningStrategies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) throws GameOverException {
        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();

        System.out.println("Enter dimension of the game: ");
        int dimension = sc.nextInt();

        System.out.println("Will there be any bot in the game or not? Y/N");
        String isBotPresent = sc.next();

        List<Player> players = new ArrayList<>();
        int iteratorNumber = dimension-1;

        if(isBotPresent.equalsIgnoreCase("Y")){
            iteratorNumber = iteratorNumber-1;
        }

        for(int i=1;i<=iteratorNumber;i++){
            // No way to take input of character in Scanner
            System.out.println("What's the name of the player number " + i);
            String playerName = sc.next();

            System.out.println("What's the character symbol of the player number " + i);
            String symbol = sc.next();

            players.add(new Player(i, playerName, symbol.charAt(0), PlayerType.HUMAN));
        }

        if(isBotPresent.equalsIgnoreCase("Y")){
            System.out.println("What's the name of the Bot: ");
            String botName = sc.next();

            System.out.println("What's the character symbol of the BOT: ");
            String botSymbol = sc.next();

            // Take bot difficulty level input, and set strategy accordingly
            System.out.println("What's the bot difficulty level: ");
            String botDifficultyLevelInput = sc.next();

            BotDifficultyLevel botDifficultyLevel = null;

            if(botDifficultyLevelInput.equals("EASY")) {
                botDifficultyLevel = BotDifficultyLevel.EASY;
            }
            else if(botDifficultyLevelInput.equals("MEDIUM")) {
                botDifficultyLevel = BotDifficultyLevel.MEDIUM;
            }
            else{
                botDifficultyLevel = BotDifficultyLevel.HARD;
            }

            players.add(new Bot(iteratorNumber+1, botName, botSymbol.charAt(0), PlayerType.BOT, botDifficultyLevel));

            // randomizes the list of players, so order of playing is completely random
            Collections.shuffle(players);
        }

        Game game = gameController.createGame(dimension, players, WinningStrategies.ORDERONE_WINNINGSTRATEGY);
        int playerIndex = -1;

        while(gameController.getGameStatus(game).equals(GameStatus.GAME_IN_PROGRESS)){
            System.out.println("\nCurrent Board Status: ");
            gameController.displayBoard(game);
            playerIndex = (playerIndex+1) % players.size();

            Move movePlayed = gameController.executeMove(game, players.get(playerIndex));

            // TODO: Implement Undo functionaliy
            System.out.println("Do you want to undo your last move? Y/N");
            String isUndoRequired = sc.next();

            if(isUndoRequired.equalsIgnoreCase("Y")){
                gameController.undoMove(game, movePlayed);
                continue;
            }

            Player winner = gameController.checkWinner(game, movePlayed);

            if(winner != null){
                System.out.println("\nWinner of Game: " + winner.getName());
                break;
            }
        }

        System.out.println("\nFINAL STATUS OF BOARD: \n");
        gameController.displayBoard(game);

        System.out.println("Do you want to replay the game? Y/N");
        String replayRequired = sc.next();
        if(replayRequired.equalsIgnoreCase("Y")){
            gameController.replayGame(game);
        }
    }
}
