package model;

import java.util.ArrayList;

public abstract class Pokemon {
    protected String name;
    protected int level;
    protected int hp;
    protected ArrayList<Attack> attacks;
    private int maxHP;
//    protected int speed;

    public Pokemon(String name, int level) {
        this.name = name;
        this.level = level;
        setHP();
        this.maxHP = this.hp;
//        setSpeed();

//        pokemonSpeeds = new ArrayList<>(Arrays.asList(45, 65, 43, 45, 50, 56, 72, 70, 90, 95, 90, 55, 60, 15, 70));
    }

    // REQUIRES: this.hp >= amount
    // MODIFIES: this
    // EFFECTS: reduces this.hp by given amount
    public void reduceHP(int amount) {
        this.hp -= amount;
    }

    // EFFECTS: returns true if pokemon health is critical, false otherwise
    public boolean isHealthCritical() {
        return this.hp < this.maxHP / 5;
    }

    public abstract void setHP();

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
}