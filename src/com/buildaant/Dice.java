package com.buildaant;

import java.util.Random;

public class Dice {
    public static int roll() {
        return (int) (Math.random() * 6) + 1;
    }
}