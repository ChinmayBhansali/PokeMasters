package model.pokemon;

import model.Pokemon;
import model.attacks.Bite;
import model.attacks.QuickAttack;
import model.attacks.Tackle;

import java.util.ArrayList;
import java.util.Arrays;

public class Rattata extends Pokemon {
    public Rattata(int level) {
        super("Rattata", level);
    }

    @Override
    public void setAttacks() {
        this.attacks = new ArrayList<>(Arrays.asList(new Tackle(), new QuickAttack(), new Bite()));
    }

    @Override
    public void setHP() {
        this.hp = 30 * this.level;
    }

//    @Override
//    public void setSpeed() {
//
//    }
}
