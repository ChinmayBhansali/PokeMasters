package model.pokemon;

import model.Pokemon;
import model.attacks.BugBite;
import model.attacks.PoisonSting;

import java.util.ArrayList;
import java.util.Arrays;

public class Weedle extends Pokemon {
    public Weedle(int level) {
        super("Weedle", level);
    }

    @Override
    public void setHP() {
        this.hp = 40 * this.level;
    }

    @Override
    public void setAttacks() {
        attacks = new ArrayList<>(Arrays.asList(new PoisonSting()));
        learnAttacks();
    }

    @Override
    protected void setPokedexNumber() {
        pokedexNumber = "013";
    }

    private void learnAttacks() {
        if (level >= 9) {
            attacks.add(new BugBite());
        }
    }
}
