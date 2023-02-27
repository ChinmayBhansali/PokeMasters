package model.pokemon;

import model.Pokemon;
import model.attacks.BugBite;

import java.util.ArrayList;
import java.util.Arrays;

public class Weedle extends Pokemon {
    public Weedle(int level) {
        super("Weedle", level);
        this.attacks = new ArrayList<>(Arrays.asList(new BugBite()));
    }

    @Override
    public void setHP() {
        this.hp = 40 * this.level;
    }
}
