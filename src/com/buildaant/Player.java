package com.buildaant;

public abstract class Player {
    private String name;
    private boolean isFirst;
    private boolean isWinner;
    private boolean autoRoll;

    public Player() {

    }

    public Player(String name) {
        this.name = name;
    }

    // business methods
    public void rollDice(Ant ant) throws NotPossibleException {
        Dice dice = new Dice();
        int roll = dice.roll();
        AntPiece piece = AntPiece.get(roll);
        if (piece == AntPiece.BODY || piece == AntPiece.HEAD) {
            try {
                ant.add(piece, this);
            } catch (NotPossibleException e) {
                System.out.println(e.getMessage());
            }
        } else if (piece == AntPiece.LEGS || piece == AntPiece.TAIL || piece == AntPiece.EYE || piece == AntPiece.ANTENNA) {
            if (ant.hasPart(AntPiece.HEAD, this) && ant.hasPart(AntPiece.BODY, this)) {
                try {
                    ant.add(piece, this);
                } catch (NotPossibleException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("You cannot build this piece until you have a body and a head.");
            }
        } else {
            System.out.println("Invalid roll. Please try again.");
        }
    }
        // TODO: execute dice method
        // .switchTurns()


    public void switchTurns() {
       // TODO: ask jay; how do we make the players switch turns
    }

    public void win() {

    }


    // access methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public boolean isWinner() {
        return isWinner;
    }
}
//