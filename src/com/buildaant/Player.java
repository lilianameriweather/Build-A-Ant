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
    public void rollDice() {
        // TODO: execute dice method
        // .switchTurns()
    }

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