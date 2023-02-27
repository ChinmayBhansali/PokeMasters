package model.pokemon;

import model.Pokemon;
import model.attacks.Tackle;
import model.attacks.WaterGun;

import java.util.ArrayList;
import java.util.Arrays;

public class Slowpoke extends Pokemon {
    public Slowpoke(int level) {
        super("Slowpoke", level);
        this.attacks = new ArrayList<>(Arrays.asList(new Tackle(), new WaterGun()));
    }

    @Override
    public void setHP() {
        this.hp = 90 * this.level;
    }
}
