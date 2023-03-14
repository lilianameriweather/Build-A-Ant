package com.buildaant;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private static final String dataFilePath= "";   //todo data/board.dat text file snipit of jshell
    private static final int NUM_BODY_PARTS = 6;


    public static Board getInstanceOf(){
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

    private final Map<Integer, AntPiece> antPieceMap = new HashMap<>(); // (diceRoll, antPiece)
    private final Map<Player, List<AntPiece>> playerPieces = new HashMap<>(); // (Player, player pieces)


//    public void update(Ant ant, Player player, Player Player2){   //update to the perspective players board
//        printBoards(, );
//        printBoard();
//    }


    public void printBoards() {
        System.out.println("            BUILD-A-ANT               ");
        System.out.println("======================================\n");

        System.out.println("Player 1 board:");
        System.out.println("-----------------");
        //printBoard();


        System.out.println("Player 2 board:");
        System.out.println("----------------");
        //printBoard();

        Collection<List<AntPiece>> allPieces = playerPieces.values(); // return collection of pieces?

        for (List<AntPiece> antPiece : allPieces) {
            System.out.printf("%s            %s             %s \n",
                    antPiece.get(1), antPiece.get(2), antPiece.get(3));
        }

    }

}