package com.buildaant;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private static final String dataFilePath= "";   //todo data/board.dat text file snipit of jshell
    private static final int NUM_BODY_PARTS = 6;


    static Board getInstanceOf(){
        Board board = null;

        if(Files.exists(Path.of("data/board.dat"))){
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(dataFilePath))){
                board =(Board) in.readObject();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            board = new Board();
        }
        return board;
    }


//    private final Map<Integer, AntPiece> antPieceMap = HashMap<>();

    public void update(){   //update to the perspective players board

    }

    public void printBuiltParts(Ant ant, Player player1, Player player2) {
        System.out.println("Body parts built:");
        for (AntPiece part : AntPiece.values()) {
            boolean player1HasPart = ant.hasPart(part, player1);
            boolean player2HasPart = ant.hasPart(part, player2);
            String partName = part.toString();
            String player1Status = player1HasPart ? "built" : "not built";
            String player2Status = player2HasPart ? "built" : "not built";
            System.out.println(partName + ": Player 1 " + player1Status + ", Player 2 " + player2Status);
        }
    }
    public void printBoards(Ant ant, Player player1, Player player2) {
        System.out.println("Player 1 board:");
        printBoard(ant, player1);
        System.out.println("Player 2 board:");
        printBoard(ant, player2);
    }

    private void printBoard(Ant ant, Player player) {
        System.out.println("Body: " + (ant.hasPart(AntPiece.BODY, player) ? "built" : "not built"));
        System.out.println("Head: " + (ant.hasPart(AntPiece.HEAD, player) ? "built" : "not built"));
        System.out.println("Legs: " + (ant.hasPart(AntPiece.LEGS, player) ? "built" : "not built"));
        System.out.println("Tail: " + (ant.hasPart(AntPiece.TAIL, player) ? "built" : "not built"));
        System.out.println("Eyes: " + (ant.hasPart(AntPiece.EYE, player) ? "built" : "not built"));
        System.out.println("Antennae: " + (ant.hasPart(AntPiece.ANTENNA, player) ? "built" : "not built"));
    }
}