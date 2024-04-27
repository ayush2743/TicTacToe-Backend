package org.example;

import org.example.controller.gameController;
import org.example.exceptions.InvalidMoveException;
import org.example.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidMoveException {
        System.out.println("Hello world!");
        Scanner scanner = new Scanner(System.in);
        gameController gameController = new gameController();

        System.out.println("Enter the board dimensions :");
        int dimension = scanner.nextInt();

        System.out.println("NOTE : ONLY ONE BOT IS ALLOWED");
        System.out.println("Enter the number of players : ");
        int playersNum = scanner.nextInt();

        List<Player> players = new ArrayList<>();
        for(int i =0 ; i<playersNum;i++){
            System.out.println("Enter Player "+(i+1)+"'s name :");
            String name = scanner.next();
            Player person = new Player(name,new Symbol((char)('A'+i)) , PlayerType.HUMAN);
            players.add(person);
        }

        if(playersNum<dimension-1){
            players.add(new Bot("Bot", new Symbol('o'), PlayerType.BOT, BotDifficultyLevel.EASY));
        }

        Game game = gameController.startGame(dimension, players);

        while (game.getGameState().equals(GameState.IN_PROGRESS)) {
            gameController.printBoard(game);
            gameController.makeMove(game);
        }

        if (!gameController.checkState(game).equals(GameState.ENDED)) {
            game.setGameState(GameState.DRAW);
            System.out.println("Game DRAW");
        } else {
            gameController.printBoard(game);
            System.out.println("Player " + gameController.getWinner(game).getName() + " is the winner");
        }
    }
}