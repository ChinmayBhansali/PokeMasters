package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public abstract class Pokemon implements Writable {
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
        this.maxHP = hp;
//        setSpeed();

//        pokemonSpeeds = new ArrayList<>(Arrays.asList(45, 65, 43, 45, 50, 56, 72, 70, 90, 95, 90, 55, 60, 15, 70));
    }

    // REQUIRES: this.hp >= amount
    // MODIFIES: this
    // EFFECTS: reduces this.hp by given amount
    public void reduceHP(int amount) {
        hp -= amount;
    }

    // EFFECTS: returns true if pokemon health is critical, false otherwise
    public boolean isHealthCritical() {
        return hp < maxHP / 5;
    }

    public abstract void setHP();

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHP() {
        return hp;
    }

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

//    public int getSpeed() {
//        return this.speed;
//    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("level", level);
        json.put("hp", hp);
        return json;
    }
}