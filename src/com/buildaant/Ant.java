package com.buildaant;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ant {
    private final Map<Player, List<AntPiece>> pieces = new HashMap<>();

    /*
     * Adds the Piece to the Ant, if possible (there are rules around this),
     * and throws exception to indicate if this is not possible (because of the rules).
     */
    public void add(AntPiece piece, Player player) throws NotPossibleException {

    }
    public boolean hasPart(AntPiece piece, Player player) {
        List<AntPiece> playerPieces = pieces.get(player);
        return playerPieces != null && playerPieces.contains(piece); // add if statement for null pointer exception
    }
}