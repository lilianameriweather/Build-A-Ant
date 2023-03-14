package com.buildaant;

public class Player {
    private String name;
    private boolean isFirst;
    private boolean isWinner;
    private boolean autoRoll;
    private Ant ant = new Ant();


    // ctors
    public Player() {

    }

    public Player(String name) {
        this.name = name;
    }

    // business methods
    public void addPiece(int roll) throws NotPossibleException {

        AntPiece piece = AntPiece.get(roll);
        ant.add(piece);
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
        return ant.isComplete();
    }

    public Ant getAnt() {
        return ant;
    }

    public void setAnt(Ant ant) {
        this.ant = ant;
    }
}
