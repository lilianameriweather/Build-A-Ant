package com.buildaant;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Ant {
    private final Set<AntPiece> pieces = new HashSet<>();
    private boolean hasHead = false;
    private boolean hasBody = false;

    /*
    public void add(AntPiece piece) throws NotPossibleException {

        if(!hasHead && !hasBody) {
           if(piece == AntPiece.BODY){
               pieces.add(piece);
               hasBody = true;
           }
           else if (piece == AntPiece.HEAD) {
               pieces.add(piece);
               hasHead = true;
           }
           else {
               throw new NotPossibleException("Ant needs a Body and Head first!");
           }
        }
        else {
            if (!pieces.contains(piece)) {
                pieces.add(piece);
            }
            else {
                throw new NotPossibleException("Ant already has " + piece);
            }
        }

    } */

    public void add(AntPiece piece) throws NotPossibleException {
        // --------------------HEAD-BODY-------------------------
        if (hasPiece(piece)) {
            throw new NotPossibleException("You Already have this piece: " + piece);
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


    public void show() {

        if(pieces.contains(AntPiece.ANTENNA)){
            System.out.println(AntPiece.ANTENNA);
        }
        if(pieces.contains(AntPiece.HEAD)) {
            System.out.println(AntPiece.HEAD);
        }
        if(pieces.contains(AntPiece.EYES)){
            System.out.println(AntPiece.EYES);
        }
        if(pieces.contains(AntPiece.BODY)){
            System.out.println(AntPiece.BODY);
        }
        if(pieces.contains(AntPiece.LEGS)){
            System.out.println(AntPiece.LEGS);
        }
        if(pieces.contains(AntPiece.TAIL)){
            System.out.println(AntPiece.TAIL);
        }
        // call ant.hasPiece(piece)
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