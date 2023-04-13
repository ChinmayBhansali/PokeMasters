package model.pokemon;

import model.Pokemon;
import model.attacks.BugBite;
import model.attacks.Tackle;

import java.util.ArrayList;
import java.util.Arrays;

public class Caterpie extends Pokemon {
    public Caterpie(int level) {
        super("Caterpie", level);
    }

    @Override
    public void setHP() {
        this.hp = 45 * this.level;
    }

    @Override
    public void setAttacks() {
        attacks = new ArrayList<>(Arrays.asList(new Tackle()));
        learnAttacks();
    }

    @Override
    protected void setPokedexNumber() {
        pokedexNumber = "010";
    }

    private void learnAttacks() {
        if (level >= 9) {
            attacks.add(new BugBite());
        }
    }
}
