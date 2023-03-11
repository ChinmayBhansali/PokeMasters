package model.pokemon;

import model.Pokemon;
import model.attacks.RazorLeaf;
import model.attacks.SeedBomb;
import model.attacks.Tackle;
import model.attacks.VineWhip;

import java.util.ArrayList;
import java.util.Arrays;

public class Bulbasaur extends Pokemon {
    public Bulbasaur(int level) {
        super("Bulbasaur", level);
    }

    @Override
    public void setHP() {
        hp = 45 * level;
    }

    @Override
    public void setAttacks() {
        attacks = new ArrayList<>(Arrays.asList(new Tackle()));
        learnAttacks();
    }

    private void learnAttacks() {
        if (this.level >= 3) {
            attacks.add(new VineWhip());
        }
        if (this.level >= 12) {
            attacks.add(new RazorLeaf());
        }
        if (level >= 18) {
            attacks.add(new SeedBomb());
        }
    }
}
