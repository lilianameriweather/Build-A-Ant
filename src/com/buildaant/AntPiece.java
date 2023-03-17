package com.buildaant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum AntPiece {

    BODY("1-body"),             // each of these calls the
    HEAD("2-head"),             // ctor below, passing in
    LEGS("3-body-and-legs"),    // the base name of the image text file
    TAIL("4-tail"),
    EYES("5-head-and-eyes"),
    ANTENNA("6-antenna");


    // Stores the mappings from dice roll to corresponding AntPiece object:
     //
     // 1 | BODY
     // 2 | HEAD
     // 3 | LEGS
     // 4 | TAIL
     // 5 | EYE
     // 6 | ANTENNA

    private static final Map<Integer, AntPiece> antPieceMap = new HashMap<>();

    // executes when AntPiece.class is loaded by the ClassLoader.
    static {
        antPieceMap.put(1, BODY);
        antPieceMap.put(2, HEAD);
        antPieceMap.put(3, LEGS);
        antPieceMap.put(4, TAIL);
        antPieceMap.put(5, EYES);
        antPieceMap.put(6, ANTENNA);
    }

    private String image;
    private List<String> imageLines;

    // constructor - loads each AntPiece's "image" from ascii art text file.
    AntPiece(String imageFile) {
        String imageFilePath = "images/" + imageFile + ".txt";

        try {
            image = Files.readString(Path.of(imageFilePath));
            imageLines = Files.readAllLines(Path.of(imageFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //access methods
    public static AntPiece get(int roll) {
        return antPieceMap.get(roll);
    }

    public List<String> getImageLines() {
        return imageLines;
    }

    public String getImage() {
        return image;
    }
}