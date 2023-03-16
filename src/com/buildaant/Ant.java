package com.buildaant;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import static com.buildaant.AntPiece.*;


public class Ant {
    private final Set<AntPiece> pieces = new HashSet<>();
    private boolean hasHead = false;
    private boolean hasBody = false;



    public void add(AntPiece piece) throws NotPossibleException {
        // --------------------HEAD-BODY-------------------------
        if (hasPiece(piece)) {
            throw new NotPossibleException("You Already have this piece:\n" + piece);
        }
        if (AntPiece.HEAD == piece) {
            pieces.add(piece);
            hasHead = true;
        }
        else if (AntPiece.BODY == piece) {
            pieces.add(piece);
            hasBody = true;
        }
        //-----------------4--possible-options-----------------------
        else if (hasHead && hasBody) {
            pieces.add(piece);
        }
        else if (hasHead) {
            if (AntPiece.ANTENNA == piece || AntPiece.EYES == piece) {
                pieces.add(piece);
            }
            else {
                throw new NotPossibleException("Only antenna and eyes can be added to head.");
            }
        }
        else if (hasBody) {
            if (AntPiece.LEGS == piece || AntPiece.TAIL == piece) {
                pieces.add(piece);
            }
            else {
                throw new NotPossibleException("Only legs and tail can be added to body.");
            }
        }
        else {
            throw new NotPossibleException("Must have head or body first");
        }
    }


//    public void show() {
//        if(pieces.contains(AntPiece.ANTENNA)){
//            System.out.println(AntPiece.ANTENNA);
//        }
//        if(pieces.contains(AntPiece.EYES) && pieces.contains(AntPiece.HEAD)){
//            System.out.println(AntPiece.EYES);
//        }
//        else if (pieces.contains(AntPiece.HEAD)) {
//            System.out.println(AntPiece.HEAD);
//        }
//        if(pieces.contains(AntPiece.LEGS) && pieces.contains(AntPiece.BODY)){
//            System.out.println(AntPiece.LEGS);
//        }
//        else if(pieces.contains(AntPiece.BODY)){
//            System.out.println(AntPiece.BODY);
//        }
//        if(pieces.contains(AntPiece.TAIL)){
//            System.out.println(AntPiece.TAIL);
//        }
//    }

    public String show() {
        String starter =
                "\n\n\n" +
                "      N O\n" +
                "     A N T\n" +
                "      T O\n" +
                "    S H O W\n" +
                "     Y E T\n" +
                "\n\n\n\n";

        StringBuilder builder = new StringBuilder((pieces.isEmpty() ? starter : ""));

        if (hasPiece(ANTENNA)) {
            builder.append(ANTENNA.getImage() + "\n");     // 6-antenna.txt
        }

        if (hasPiece(EYES) && hasPiece(HEAD)) {
            builder.append(EYES.getImage() + "\n");        // 5-head-and-eyes.txt
        }
        else if (hasPiece(HEAD) && !hasPiece(EYES)) {
            builder.append(HEAD.getImage() + "\n");        // 2-head.txt
        }

        if (hasPiece(LEGS) && hasPiece(BODY)) {
            builder.append(LEGS.getImage() + "\n");        // 3-body-and-legs.txt
        }
        else if (hasPiece(BODY) && !hasPiece(LEGS)) {
            builder.append(BODY.getImage() + "\n");        // 1-body.txt
        }

        if (hasPiece(TAIL)) {
            builder.append(TAIL.getImage() + "\n");        // 4-tail.txt
        }

        String fullImage = builder.toString();
        System.out.println(fullImage);
        return fullImage;
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