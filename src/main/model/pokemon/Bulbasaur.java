package model.pokemon;

import model.Pokemon;
import model.attacks.Tackle;
import model.attacks.VineWhip;

import java.util.ArrayList;
import java.util.Arrays;

public class Bulbasaur extends Pokemon {
    public Bulbasaur(int level) {
        super("Bulbasaur", level);
    }

    @Override
    public void setAttacks() {
        this.attacks = new ArrayList<>(Arrays.asList(new Tackle(), new VineWhip()));
    }

    @Override
    public void setHP() {
        this.hp = 45 * this.level;
    }

//    @Override
//    public void setSpeed() {
//
//    }
}
