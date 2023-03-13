package com.buildaant;

public class ComputerPlayer extends Player {
    private boolean autoRoll = true;

    public ComputerPlayer(String name, boolean autoRoll) {
        super(name);
        this.autoRoll = autoRoll;
    }


}