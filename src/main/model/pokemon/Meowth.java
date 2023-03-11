package model.pokemon;

import model.Pokemon;
import model.attacks.FakeOut;
import model.attacks.Feint;
import model.attacks.PayDay;
import model.attacks.Scratch;

import java.util.ArrayList;
import java.util.Arrays;

public class Meowth extends Pokemon {
    public Meowth(int level) {
        super("Meowth", level);
    }

    @Override
    public void setHP() {
        hp = 40 * level;
    }

    @Override
    public void setAttacks() {
        attacks = new ArrayList<>(Arrays.asList(new FakeOut()));
        learnAttacks();
    }

    private void learnAttacks() {
        if (level >= 4) {
            attacks.add(new Feint());
        }
        if (level >= 8) {
            attacks.add(new Scratch());
        }
        if (level >= 12) {
            attacks.add(new PayDay());
        }
    }
}
