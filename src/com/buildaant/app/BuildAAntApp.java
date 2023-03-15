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
import java.util.Scanner;

public class BuildAAntApp {

    private final Player player1 = new Player();
    private final Player player2 = new Player("Computer");
    private boolean gameOver = false;
    private final Prompter prompter;


    public BuildAAntApp(Prompter prompter) {
        this.prompter = prompter;
    }

    public void execute() {
        welcome();
        intro();
        String name = promptForPlayerName();
        player1.setName(name);
        play();
    }

    private void play() {
        while (!gameOver) {
            promptForPlayerRoll();
            rollDice(player1);
            rollDice(player2);
            showAnt();

            // pause and clear as needed


            gameOver = player1.getAnt().isComplete() || player2.getAnt().isComplete();
            if (gameOver) {
                Player winner = player1.getAnt().isComplete() ? player1 : player2;
                System.out.println(winner.getName() + " has won!");
            }
        }
    }

    private void showAnt() {
        System.out.println(player1.getName() + "'s ant:");
        player1.getAnt().show();
        System.out.println(player2.getName() + "'s ant:");
        player2.getAnt().show();
    }

    private void rollDice(Player player) {
        try {
            int roll = Dice.roll();
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
    }
}