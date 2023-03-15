package com.buildaant;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Ant {
    private final Set<AntPiece> pieces = new HashSet<>();
    private boolean hasHead = false;
    private boolean hasBody = false;

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
}