package com.buildaant.app;

import com.buildaant.Board;

public class BuildAAntApp {

    private final Board board = Board.getInstanceOf();


    public void execute() {
        welcome();
        showBoards();
    }



    private void showBoards() {
        board.printBoards();
    }

    private void welcome() {
        System.out.println("W E L C O M E     T O    B U I L D - A - A N T");
        System.out.println();
    }
}