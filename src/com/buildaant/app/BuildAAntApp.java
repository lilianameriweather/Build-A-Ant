package com.buildaant.app;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.buildaant.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import static com.apps.util.Console.blankLines;
import static com.apps.util.Console.pause;

public class BuildAAntApp {

    private final Player player1 = new Player();
    private final Player player2 = new Player("Computer");
    private final Prompter prompter;
    private boolean gameOver = false;
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
            Console.blankLines(1);
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

    private void playTurn() {
        if (currentPlayer == player1) {
            promptForPlayerRoll();
        } else {
            // Add a small delay for the computer player to simulate a more natural flow
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        rollDice(currentPlayer);
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private void playRound() {
        playTurn();
        playTurn();
        showAnt();
    }


    private void play() {
        while (!gameOver) {
            playRound();
            if (player1.getAnt().isComplete() && player2.getAnt().isComplete()) {
                System.out.println(player1.getName() + " and " + player2.getName() + " both built their ants and tied!");
                gameOver = true;
                gameOver();
                }
                else if(player1.getAnt().isComplete()) {
                    System.out.println(player1.getName() + " built-a-ant first!");
                    gameOver = true;
                    gameOver();
                }
                else if (player2.getAnt().isComplete()) {
                System.out.println(player2.getName() + " built-a-ant first!");
                gameOver = true;
                gameOver();
                }
        }
    }

//    private void showAnt() {
//        System.out.println("===========================");
//        System.out.println(player1.getName() + "'s ant:");
//        System.out.println("===========================");
//        player1.getAnt().show();
//
//        try {
//            Thread.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("===========================");
//        System.out.println(player2.getName() + "'s ant:");
//        System.out.println("===========================");
//        player2.getAnt().show();
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
        blankLines(1);
        prompter.prompt("Press Enter to Continue");
        Console.clear();
    }

    private void welcome() {
        Console.clear();
        try {
            String introTxt = Files.readString(Path.of("introtxt/welcomebanner.txt"));
            prompter.info(introTxt);
        } catch (IOException e) {
            e.printStackTrace();
        }
        blankLines(1);
        String welcome = "W  E  L  C  O  M  E    T  O    B  U  I  L  D  -  A  -  A  N  T";
        for(int i = 0; i < welcome.length(); i++){
            System.out.print(welcome.charAt(i));
            Console.pause(100);
        }
        Console.clear();
        blankLines(2);
        System.out.println(Ant.complete);
        pause(2500);


        blankLines(1);
        Console.pause(5000);
        Console.clear();
    }

    private void gameOver() {
        try {
            String gameOverTxt = Files.readString(Path.of("introtxt/gameover.txt"));
            prompter.info(gameOverTxt);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Console.pause(20000);
        prompter.prompt("Press Enter to Exit");
        blankLines(2);

    }

}