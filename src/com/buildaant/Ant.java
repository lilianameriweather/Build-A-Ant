package com.buildaant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import static com.buildaant.AntPiece.*;


public class Ant {
    private static final String imagesDir = "images/";

    // populated via static initializer block at bottom
    public static String complete;
    private static List<String> top;
    private static List<String> bottom;
    private static List<String> none;

    private static List<String> antennaNone;
    private static List<String> headNone;
    private static List<String> bodyNone;
    private static List<String> tailNone;

    static {
        try {
            top = Files.readAllLines(Path.of(imagesDir + "top.txt"));
            bottom = Files.readAllLines(Path.of(imagesDir + "bottom.txt"));
            none = Files.readAllLines(Path.of(imagesDir + "none.txt"));
            antennaNone = Files.readAllLines(Path.of(imagesDir + "6-antenna-none.txt"));
            headNone = Files.readAllLines(Path.of(imagesDir + "2-head-none.txt"));
            bodyNone = Files.readAllLines(Path.of(imagesDir + "1-body-none.txt"));
            tailNone = Files.readAllLines(Path.of(imagesDir + "4-tail-none.txt"));

            // build the image of a completed Ant
            StringBuilder builder = new StringBuilder();
            top.forEach(line -> builder.append(line + "\n"));
            builder.append(Files.readString(Path.of(imagesDir + "ant-complete.txt")) + "\n");
            bottom.forEach(line -> builder.append(line + "\n"));
            complete = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final Set<AntPiece> pieces = new HashSet<>();
    private boolean hasHead = false;
    private boolean hasBody = false;

    public void add(AntPiece piece) throws NotPossibleException {
        // --------------------HEAD-BODY-------------------------
        if (pieces.contains(piece)) {
            throw new NotPossibleException("        Sorry, you already have this piece: " + piece);
        }
        if (HEAD == piece) {
            pieces.add(piece);
            hasHead = true;
            System.out.println("        " + piece + " ADDED");
        } else if (BODY == piece) {
            pieces.add(piece);
            hasBody = true;
            System.out.println("        " + piece + " ADDED");
        }
        //-----------------4--possible-options-----------------------
        else if (hasHead && hasBody) {
            pieces.add(piece);
            System.out.println("        " + piece + " ADDED");
        } else if (hasHead) {
            if (ANTENNA == piece || EYES == piece) {
                pieces.add(piece);
                System.out.println("        " + piece + " ADDED");
            } else {
                throw new NotPossibleException("        Sorry, only antenna and eyes can be added to head!");
            }
        } else if (hasBody) {
            if (LEGS == piece || TAIL == piece) {
                pieces.add(piece);
                System.out.println("        " + piece + " ADDED");
            } else {
                throw new NotPossibleException("        Sorry, only legs and tail can be added to body!");
            }
        } else {
            throw new NotPossibleException("        Sorry, you must have head or body first!");
        }
    }

    public void show() {
        getImageLines().forEach(System.out::println);
    }

    public List<String> getImageLines() {
        List<String> lines = new ArrayList<>();

        lines.addAll(top);

        if (pieces.isEmpty()) {
            lines.addAll(none);
        } else {
            if (hasPiece(ANTENNA)) {
                lines.addAll(ANTENNA.getImageLines());
            } else {
                lines.addAll(antennaNone);
            }

            if (hasPiece(HEAD)) {
                if (hasPiece(EYES)) {
                    lines.addAll(EYES.getImageLines());
                } else {
                    lines.addAll(HEAD.getImageLines());
                }
            } else {
                lines.addAll(headNone);
            }

            if (hasPiece(BODY)) {
                if (hasPiece(LEGS)) {
                    lines.addAll(LEGS.getImageLines());
                } else {
                    lines.addAll(BODY.getImageLines());
                }
            } else {
                lines.addAll(bodyNone);
            }

            if (hasPiece(TAIL)) {
                lines.addAll(TAIL.getImageLines());
            } else {
                lines.addAll(tailNone);
            }
        }
        lines.addAll(bottom);
        return lines;
    }

    public boolean hasPiece(AntPiece piece) {
        return pieces.contains(piece);
    }

    public boolean isComplete() {
        return pieces.size() == 6;
    }

    @Override
    public String toString() {
        return pieces.toString() + ", isComplete=" + isComplete();
    }

}