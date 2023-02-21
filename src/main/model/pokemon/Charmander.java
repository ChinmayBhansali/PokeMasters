package model.pokemon;

import model.Pokemon;
import model.attacks.Ember;
import model.attacks.Scratch;

import java.util.ArrayList;
import java.util.Arrays;

public class Charmander extends Pokemon {
    public Charmander(int level) {
        super("Charmander", level);
    }

    @Override
    public void setAttacks() {
        this.attacks = new ArrayList<>(Arrays.asList(new Scratch(), new Ember()));
    }

    @Override
    public void setHP() {
        this.hp = 39 * this.level;
    }

//    @Override
//    public void setSpeed() {
//
//    }
}
