package com.buildaant.app;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.buildaant.AntPiece;
import com.buildaant.Dice;
import com.buildaant.NotPossibleException;
import com.buildaant.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class BuildAAntApp {

    private final Player player1 = new Player();
    private final Player player2 = new Player("Computer");
    private boolean gameOver = false;
    private final Prompter prompter;
    private Player firstPlayer;
    private Player nextPlayer;


    public BuildAAntApp(Prompter prompter) {
        this.prompter = prompter;
    }

    public void execute() {
        welcome();
        intro();
        String name = promptForPlayerName();
        player1.setName(name);
        setup();
        play();
    }

    private void setup() {
        int player1Roll = 0;
        int player2Roll = 0;
        System.out.println("Determining who goes first by rolling highest number...");

        while (player1Roll == player2Roll) {
            if (player1Roll != 0) {
                System.out.println("It's a tie! Let's roll again...");
            }
            promptForPlayerRoll();
            player1Roll = Dice.roll();
            player2Roll = Dice.roll();
            System.out.println(player1.getName() + " rolled " + player1Roll);
            System.out.println(player2.getName() + " rolled " + player2Roll);
            Console.blankLines(1);
            }
            if (player1Roll > player2Roll) {
            System.out.println(player1.getName() + " goes first.");
            firstPlayer= player1;
            nextPlayer = player2;

            }
            if (player1Roll < player2Roll) {
            System.out.println(player2.getName() + " goes first.");
            firstPlayer = player2;
            nextPlayer = player1;
            }
    }

    private void switchCurrentPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    private void playTurn(Player player) {
        if (currentPlayer == player1) {
            promptForPlayerRoll();
        }
        rollDice(currentPlayer);
    }

    private void play() {
        int turns = 0;
        while (!gameOver) {
            playTurn(currentPlayer);
            playTurn();
            if (currentPlayer.getAnt().isComplete()) {
                if (turns % 2 == 1) { //equal number of rolls
                    System.out.println(player1.getName() + " and " + player2.getName() + " both built their ants and tied!");
                    gameOver = true;
                } else {
                    System.out.println(currentPlayer.getName() + " built-a-ant first!");
                    gameOver = true;
                }
            }
                turns++;
                switchCurrentPlayer();
                if (!gameOver && turns % 2 == 0 ) {
                    showAnt();
                }
            }
        }

// pause and clear as needed
//    private void play() {
//        while (!gameOver) {
//
//            promptForPlayerRoll();
//            rollDice(player1);
//            rollDice(player2);
//            showAnt();
//
//            // pause and clear as needed
//
//
//            gameOver = player1.getAnt().isComplete() || player2.getAnt().isComplete();
//            if (gameOver) {
//                Player winner = player1.getAnt().isComplete() ? player1 : player2;
//                System.out.println(winner.getName() + " has won!");
//            }
//        }
//    }

    private void showAnt() {
        List<String> player1Lines = player1.getAnt().getImageLines();
        List<String> player2Lines = player2.getAnt().getImageLines();

        System.out.println("================================      ================================");
        System.out.printf("%-45s %-45s\n", player1.getName() + "'s Ant:", player2.getName() + "'s Ant:");
        System.out.println("================================      ================================");

        for (int i = 0; i < player1Lines.size(); i++) {
            System.out.println(player1Lines.get(i) + "        " + player2Lines.get(i));
        }
    }

    private void rollDice(Player player) {
        try {
            int roll = Dice.roll();
            System.out.println(player.getName() + " rolled a " + roll + ": ");
            player.addPiece(roll);
        } catch (NotPossibleException e) {
            System.out.println(e.getMessage());
        }
    }


    private void promptForPlayerRoll() {
        prompter.prompt("Press Enter to Roll Dice\n");
    }

    private String promptForPlayerName() {
        String name = null;

        boolean validInput = false;
        while (!validInput) {
            String input = prompter.prompt("Please enter your name:").trim();
            if (input.matches("([A-Za-z])+")) {
                name = input;
                validInput = true;
            } else {
                System.out.println("Invalid input. Name cannot be empty or have numbers.");
            }
        }
        return name;
    }

    private void intro() {
        Console.clear();

        try {
            String introTxt = Files.readString(Path.of("introtxt/intro.txt"));
            prompter.info(introTxt);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Console.blankLines(1);
    }


    private void welcome() {
        Console.clear();
        try {
            String introTxt = Files.readString(Path.of("introtxt/welcomebanner.txt"));
            prompter.info(introTxt);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Console.blankLines(1);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Console.clear();
    }
}