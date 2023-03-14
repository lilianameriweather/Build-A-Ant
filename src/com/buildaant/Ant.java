package com.buildaant;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ant {
    private final Set<AntPiece> pieces = new HashSet<>();
    private boolean hasHead = false;
    private boolean hasBody = false;

    /*
     * Adds the Piece to the Ant, if possible (there are rules around this),
     * and throws exception to indicate if this is not possible (because of the rules).
     */
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
        // if the ant has 'antenna' then show antenna => sout AntPiece.ANTENNA
        // if the ant has head, show head
        // if ant has head and eyes show EYES
        //if body, show BODY
        // if body and legs, show BODY and LEGS
        // if tail show TAIL
        // call ant.hasPiece(piece)

    }

    public boolean hasPiece(AntPiece piece) {
        return pieces.contains(piece);
    }

    public boolean isComplete() {
        return pieces.size() == 6;
    }
}