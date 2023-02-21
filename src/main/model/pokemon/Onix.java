package model.pokemon;

import model.Pokemon;
import model.attacks.Bind;
import model.attacks.RockThrow;
import model.attacks.SmackDown;
import model.attacks.Tackle;

import java.util.ArrayList;
import java.util.Arrays;

public class Onix extends Pokemon {
    public Onix(int level) {
        super("Onix", level);
    }

    @Override
    public void setAttacks() {
        this.attacks = new ArrayList<>(Arrays.asList(new Bind(), new RockThrow(), new Tackle(), new SmackDown()));
    }

    @Override
    public void setHP() {
        this.hp = 35 * this.level;
    }

//    @Override
//    public void setSpeed() {
//
//    }
}
