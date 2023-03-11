package model.pokemon;

import model.Pokemon;
import model.attacks.Gust;
import model.attacks.QuickAttack;
import model.attacks.Tackle;
import model.attacks.Twister;

import java.util.ArrayList;
import java.util.Arrays;

public class Pidgey extends Pokemon {
    public Pidgey(int level) {
        super("Pidgey", level);
    }

    @Override
    public void setHP() {
        hp = 40 * level;
    }

    @Override
    public void setAttacks() {
        attacks = new ArrayList<>(Arrays.asList(new Tackle()));
        learnAttacks();
    }

    private void learnAttacks() {
        if (level >= 9) {
            attacks.add(new Gust());
        }
        if (level >= 13) {
            attacks.add(new QuickAttack());
        }
        if (level >= 21) {
            attacks.add(new Twister());
        }
    }
}
