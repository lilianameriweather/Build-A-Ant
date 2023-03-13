package com.buildaant;

public class HumanPlayer extends Player {
    private boolean autoRoll = false;

    public HumanPlayer(String name, boolean autoRoll) {
        super(name);
        this.autoRoll = autoRoll;
    }
}