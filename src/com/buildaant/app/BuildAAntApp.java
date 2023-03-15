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

    //private final Board board = Board.getInstanceOf();
    private final Player player1 = new Player();
    private final Player player2 = new Player("Computer");
    private boolean gameOver = false;
    private Prompter prompter;
    private final Scanner scanner = new Scanner(System.in);

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
            // isComplete();
            // gameOver = player1.isComplete() || player2.isComplete()
        }
    }

    private void showAnt() {
        System.out.println();
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
        // scanner
        System.out.println("Press Enter to Roll Dice");
    }

    private String promptForPlayerName() {
        String name = null;

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Please enter your name:");
            String input = scanner.nextLine().trim();
            if (input.matches("\\d{1,100}")) {
                if (name.length() > 0 && name.matches("[a-zA-Z]+")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            }
        }
    }
    // TODO: Scanner
    // must be at least one char
    // ([A-Z][a-z])+
        return null;
}

    private void intro() {
        Console.clear();

        try {
            String introTxt = Files.readString(Path.of("introtxt/intro.txt"));
            prompter.info(introTxt);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Console.blankLines(3);
    }


    private void welcome() {
        System.out.println("W E L C O M E    T O    B U I L D - A - A N T");

        System.out.println();
    }
}