package com.buildaant;

import java.util.Random;

class Dice {
    private int value;

    public Dice() { // default die sides
        this.value = 6;
    }

    public Dice(int value) {
        this.value = value;
    }

    public int roll() {
        return (int) (Math.random() * value) + 1;
    }


}