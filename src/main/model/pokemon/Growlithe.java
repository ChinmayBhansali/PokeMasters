package model.pokemon;

import model.Pokemon;
import model.attacks.Bite;
import model.attacks.Ember;

import java.util.ArrayList;
import java.util.Arrays;

public class Growlithe extends Pokemon {
    public Growlithe(int level) {
        super("Growlithe", level);
    }

    @Override
    public void setAttacks() {
        this.attacks = new ArrayList<>(Arrays.asList(new Ember(), new Bite()));
    }

    @Override
    public void setHP() {
        this.hp = 55 * this.level;
    }

//    @Override
//    public void setSpeed() {
//
//    }
}
