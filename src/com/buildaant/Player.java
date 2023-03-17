package com.buildaant;

public class Player {
    private String name;
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


    // access methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ant getAnt() {
        return ant;
    }
}
