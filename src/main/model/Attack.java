package model;

import java.util.ArrayList;

public abstract class Attack {

    private ArrayList<String> attacks;
    private ArrayList<Integer> powers;
    private String name;
    protected int power;
//    private ArrayList<String> types;
//    protected double acc;

    public Attack(String name) {

        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getPower() {
        return this.power;
    }
}