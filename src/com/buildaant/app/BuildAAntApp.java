package com.buildaant.app;

import com.buildaant.Dice;
import com.buildaant.NotPossibleException;
import com.buildaant.Player;

public class BuildAAntApp {

    //private final Board board = Board.getInstanceOf();
    private final Player player1 = new Player();
    private final Player player2 = new Player("Computer");
    private boolean gameOver = false;

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
        }
        catch (NotPossibleException e){
            System.out.println(e.getMessage());
        }
    }


    private void promptForPlayerRoll() {
        // scanner
        System.out.println("Press Enter to Roll Dice");
    }

    private String promptForPlayerName() {
        // TODO: Scanner
        // must be at least one char
        // ([A-Z][a-z])+
        return null;
    }

    private void intro() {
        // TODO: rules
    }


    private void welcome() {
        System.out.println("W E L C O M E    T O    B U I L D - A - A N T");

        System.out.println();
    }
}