package model.pokemon;

import model.Pokemon;
import model.attacks.AerialAce;
import model.attacks.Assurance;
import model.attacks.FuryAttack;
import model.attacks.Peck;

import java.util.ArrayList;
import java.util.Arrays;

public class Spearow extends Pokemon {
    public Spearow(int level) {
        super("Spearow", level);
    }

    @Override
    public void setHP() {
        hp = 40 * level;
    }

    @Override
    public void setAttacks() {
        attacks = new ArrayList<>(Arrays.asList(new Peck()));
        learnAttacks();
    }

    @Override
    protected void setPokedexNumber() {
        pokedexNumber = "021";
    }

    private void learnAttacks() {
        if (level >= 8) {
            attacks.add(new Assurance());
        }
        if (level >= 11) {
            attacks.add(new FuryAttack());
        }
        if (level >= 15) {
            attacks.add(new AerialAce());
        }
    }
}
