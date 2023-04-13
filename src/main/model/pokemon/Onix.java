package model.pokemon;

import model.Pokemon;
import model.attacks.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Onix extends Pokemon {
    public Onix(int level) {
        super("Onix", level);
    }

    @Override
    public void setHP() {
        hp = 35 * level;
    }

    @Override
    public void setAttacks() {
        attacks = new ArrayList<>(Arrays.asList(new Bind(), new RockThrow(), new Tackle()));
        learnAttacks();
    }

    @Override
    protected void setPokedexNumber() {
        pokedexNumber = "095";
    }

    private void learnAttacks() {
        if (level >= 4) {
            attacks.add(new SmackDown());
        }
    }
}
