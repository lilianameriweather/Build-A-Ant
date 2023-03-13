package com.buildaant;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private static final String dataFilePath= "";   //todo data/board.dat text file snipit of jshell

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

    private final Map<Integer, AntPiece> antPieceMap = HashMap<>();

    public void update(){   //update to the perspective players board

    }




}