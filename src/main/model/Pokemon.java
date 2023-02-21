package model;

import java.util.ArrayList;

public abstract class Pokemon {
    protected String name;
    protected int level;
    protected int hp;
    protected ArrayList<Attack> attacks;
    protected int maxHP;
//    protected int speed;
//    private ArrayList<Integer> pokemonSpeeds;

    public Pokemon(String name, int level) {
        this.name = name;
        this.level = level;
        setAttacks();
        setHP();
        this.maxHP = this.hp;
//        setSpeed();

//        pokemonSpeeds = new ArrayList<>(Arrays.asList(45, 65, 43, 45, 50, 56, 72, 70, 90, 95, 90, 55, 60, 15, 70));
    }

    // REQUIRES: this.hp >= amount
    // MODIFIES: this.hp
    // EFFECTS: reduces this.hp by given amount
    public void reduceHP(int amount) {
        this.hp -= amount;
    }

    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.level;
    }

    public int getHP() {
        return this.hp;
    }

    public ArrayList<Attack> getAttacks() {
        return this.attacks;
    }

//    public int getSpeed() {
//        return this.speed;
//    }

    public abstract void setAttacks();

    public abstract void setHP();

//    public abstract void setSpeed();

    // MODIFIES: this.hp
    // EFFECTS: restore this.hp to default
    public void restoreHealth() {
        setHP();
    }

    // EFFECTS: returns true if pokemon health is critical, false otherwise
    public boolean isHealthCritical() {
        return this.hp < this.maxHP / 5;
    }
}