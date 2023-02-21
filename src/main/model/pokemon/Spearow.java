package model.pokemon;

import model.Pokemon;
import model.attacks.Assurance;
import model.attacks.Peck;

import java.util.ArrayList;
import java.util.Arrays;

public class Spearow extends Pokemon {
    public Spearow(int level) {
        super("Spearow", level);
    }

    @Override
    public void setAttacks() {
        this.attacks = new ArrayList<>(Arrays.asList(new Peck(), new Assurance()));
    }

    @Override
    public void setHP() {
        this.hp = 40 * this.level;
    }

//    @Override
//    public void setSpeed() {
//
//    }
}
