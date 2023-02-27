package model.pokemon;

import model.Pokemon;
import model.attacks.BugBite;
import model.attacks.Tackle;

import java.util.ArrayList;
import java.util.Arrays;

public class Caterpie extends Pokemon {
    public Caterpie(int level) {
        super("Caterpie", level);
        this.attacks = new ArrayList<>(Arrays.asList(new Tackle(), new BugBite()));
    }

    @Override
    public void setHP() {
        this.hp = 45 * this.level;
    }
}
