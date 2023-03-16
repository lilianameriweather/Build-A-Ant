package com.buildaant.app;

import com.apps.util.Console;
import com.apps.util.Prompter;
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
    private Player currentPlayer;


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
            }
            if (player1Roll > player2Roll) {
            System.out.println(player1.getName() + " goes first.");
            currentPlayer= player1;
            }
            if (player1Roll < player2Roll) {
            System.out.println(player2.getName() + " goes first.");
            currentPlayer = player2;
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

            if (currentPlayer.getAnt().isComplete()) {
                if (turns % 2 == 1) {
                    System.out.println(player1.getName() + " and " + player2.getName() + " both built their ants and tied!");
                    gameOver = true;
                } else {
                    System.out.println(currentPlayer.getName() + " built-a-ant first");
                    gameOver = true;
                }
            }
            switchCurrentPlayer();
            if (currentPlayer == player1) {
                turns++;
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
        System.out.println("===========================");
        System.out.println(player1.getName() + "'s ant:");
        System.out.println("===========================");
        player1.getAnt().show();
        System.out.println("===========================");
        System.out.println(player2.getName() + "'s ant:");
        System.out.println("===========================");
        player2.getAnt().show();
    }

//    private void showAnt() {
//        Console.clear();
//
//        String player1Ant = player1.getAnt().show();
//        String player2Ant = player2.getAnt().show();
//        System.out.println("==================================  ======================================");
//        System.out.printf("%-55s %-55s\n", player1.getName() + "'s Ant:", player2.getName() + "'s Ant:");
//        System.out.println("==================================  =====================================");
//        System.out.printf("\n%-80s%-80s\n", player1Ant, player2Ant);
//    }

    private void rollDice(Player player) {
        try {
            int roll = Dice.roll();
            System.out.println(player.getName() + " rolled a " + roll);
            player.addPiece(roll);
        } catch (NotPossibleException e) {
            System.out.println(e.getMessage());
        }
    }


    private void promptForPlayerRoll() {
        prompter.prompt("Press Enter to Roll Dice");
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