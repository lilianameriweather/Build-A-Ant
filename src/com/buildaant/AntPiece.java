package com.buildaant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

enum AntPiece {
    BODY("body"),  // each of these calls the
    HEAD("head"),  // ctor below, passing in
    LEGS("legs"),  // the base name of the image text file
    TAIL("tail"),
    EYE("eye"),
    ANTENNA("antenna");

    private String image;

    // ctor - loads each AntPiece's "image" from ascii art text file.
    //  images/body.txt
    //  images/head.txt, etc.
    AntPiece(String imageFile) {
        try {
            image = Files.readString(Path.of("images/" + imageFile + ".txt"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return image;
    }

    // statics
    /*
     * Stores the mappings from dice roll to corresponding AntPiece object:
     *
     * 1 | BODY
     * 2 | HEAD
     * 3 | LEGS
     * 4 | TAIL
     * 5 | EYE
     * 6 | ANTENNA
     */
    private static final Map<Integer,AntPiece> antPieceMap = new HashMap<>();

    // executes when AntPiece.class is loaded by the ClassLoader.
    static {
        antPieceMap.put(1, BODY);
        antPieceMap.put(2, HEAD);
        antPieceMap.put(3, LEGS);
        antPieceMap.put(4, TAIL);
        antPieceMap.put(5, EYE);
        antPieceMap.put(6, ANTENNA);
    }

    /*
     * Convenience method for controller or other client, so that it can
     * pass in the dice roll and get the corresponding AntPiece back.
     * (Otherwise, you'll likely have an annoying switch-case in the client.)
     */
    public static AntPiece get(int diceRoll) {
        return antPieceMap.get(diceRoll);
    }
}