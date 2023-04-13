package model.pokemon;

import model.Pokemon;
import model.attacks.Astonish;
import model.attacks.Bulldoze;
import model.attacks.MudSlap;
import model.attacks.Scratch;

import java.util.ArrayList;
import java.util.Arrays;

public class Diglett extends Pokemon {
    public Diglett(int level) {
        super("Diglett", level);
    }

    @Override
    public void setHP() {
        hp = 10 * level;
    }

    @Override
    public void setAttacks() {
        attacks = new ArrayList<>(Arrays.asList(new Scratch()));
        learnAttacks();
    }

    @Override
    protected void setPokedexNumber() {
        pokedexNumber = "050";
    }

    private void learnAttacks() {
        if (level >= 8) {
            attacks.add(new Astonish());
        }
        if (level >= 12) {
            attacks.add(new MudSlap());
        }
        if (level >= 16) {
            attacks.add(new Bulldoze());
        }
    }
}
