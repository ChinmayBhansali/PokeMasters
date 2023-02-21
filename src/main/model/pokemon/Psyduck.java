package model.pokemon;

import model.Pokemon;
import model.attacks.Confusion;
import model.attacks.Scratch;
import model.attacks.WaterGun;

import java.util.ArrayList;
import java.util.Arrays;

public class Psyduck extends Pokemon {
    public Psyduck(int level) {
        super("Psyduck", level);
    }

    @Override
    public void setAttacks() {
        this.attacks = new ArrayList<>(Arrays.asList(new Scratch(), new WaterGun(), new Confusion()));
    }

    @Override
    public void setHP() {
        this.hp = 50 * this.level;
    }

//    @Override
//    public void setSpeed() {
//
//    }
}
