package model.pokemon;

import model.Pokemon;
import model.attacks.Feint;
import model.attacks.QuickAttack;
import model.attacks.Spark;
import model.attacks.ThunderShock;

import java.util.ArrayList;
import java.util.Arrays;

public class Pikachu extends Pokemon {
    public Pikachu(int level) {
        super("Pikachu", level);
    }

    @Override
    public void setHP() {
        hp = 35 * level;
    }

    @Override
    public void setAttacks() {
        attacks = new ArrayList<>(Arrays.asList(new QuickAttack(), new ThunderShock()));
        learnAttacks();
    }

    private void learnAttacks() {
        if (level >= 16) {
            attacks.add(new Feint());
        }
        if (level >= 20) {
            attacks.add(new Spark());
        }
    }
}
