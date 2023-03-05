package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Pokemon implements Writable {
    protected String name;
    protected int level;
    protected int hp;
    protected ArrayList<Attack> attacks;
    private int xp;
    private int maxHP;
//    protected int speed;

    public Pokemon(String name, int level) {
        this.name = name;
        this.level = level;
        setHP();
        maxHP = hp;
        xp = level * level * 100;
//        setSpeed();

//        pokemonSpeeds = new ArrayList<>(Arrays.asList(45, 65, 43, 45, 50, 56, 72, 70, 90, 95, 90, 55, 60, 15, 70));
    }

    // REQUIRES: this.hp >= healthPoints
    // MODIFIES: this
    // EFFECTS: reduces this.hp by given healthPoints
    public void reduceHP(int healthPoints) {
        hp -= healthPoints;
    }

    // MODIFIES: this
    // EFFECTS: adds exp to this.xp
    public void gainXP(int exp) {
        xp += exp;
    }

    // MODIFIES: this
    // EFFECTS: if this pokemon has sufficient xp then adds 1 to level and returns true, otherwise returns false
    public boolean levelUp() {
        if (xp >= (level + 1) * (level + 1) * 100) {
            level++;
            return true;
        }
        return false;
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

    public String getAttackNames() {
        ArrayList<String> attackNames = new ArrayList<>();
        for (Attack a : attacks) {
            attackNames.add(a.getName());
        }
        String attacks = Arrays.toString(attackNames.toArray()).replace("[", "").replace("]", "");

        return attacks;
    }

    public int getXP() {
        return xp;
    }

//    public int getSpeed() {
//        return this.speed;
//    }

    // EFFECTS: converts this pokemon to a JSON object and returns it
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("level", level);
        json.put("hp", hp);
        json.put("xp", xp);
        return json;
    }
}