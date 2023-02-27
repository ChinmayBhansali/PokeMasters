package model.pokemon;

import model.Pokemon;
import model.attacks.RapidSpin;
import model.attacks.Tackle;
import model.attacks.WaterGun;

import java.util.ArrayList;
import java.util.Arrays;

public class Squirtle extends Pokemon {
    public Squirtle(int level) {
        super("Squirtle", level);
        this.attacks = new ArrayList<>(Arrays.asList(new Tackle(), new WaterGun(), new RapidSpin()));
    }

    @Override
    public void setHP() {
        this.hp = 44 * this.level;
    }
}
