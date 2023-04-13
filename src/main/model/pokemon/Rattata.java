package model.pokemon;

import model.Pokemon;
import model.attacks.Bite;
import model.attacks.QuickAttack;
import model.attacks.Tackle;
import model.attacks.TakeDown;

import java.util.ArrayList;
import java.util.Arrays;

public class Rattata extends Pokemon {
    public Rattata(int level) {
        super("Rattata", level);
    }

    @Override
    public void setHP() {
        this.hp = 30 * this.level;
    }

    @Override
    public void setAttacks() {
        attacks = new ArrayList<>(Arrays.asList(new Tackle()));
        learnAttacks();
    }

    @Override
    protected void setPokedexNumber() {
        pokedexNumber = "019";
    }

    private void learnAttacks() {
        if (level >= 4) {
            attacks.add(new QuickAttack());
        }
        if (level >= 10) {
            attacks.add(new Bite());
        }
        if (level >= 16) {
            attacks.add(new TakeDown());
        }
    }
}
