package model.pokemon;

import model.Pokemon;
import model.attacks.Confusion;
import model.attacks.Tackle;
import model.attacks.WaterGun;
import model.attacks.WaterPulse;

import java.util.ArrayList;
import java.util.Arrays;

public class Slowpoke extends Pokemon {
    public Slowpoke(int level) {
        super("Slowpoke", level);
    }

    @Override
    public void setHP() {
        hp = 90 * level;
    }

    @Override
    public void setAttacks() {
        attacks = new ArrayList<>(Arrays.asList(new Tackle()));
        learnAttacks();
    }

    private void learnAttacks() {
        if (level >= 6) {
            attacks.add(new WaterGun());
        }
        if (level >= 12) {
            attacks.add(new Confusion());
        }
        if (level >= 18) {
            attacks.add(new WaterPulse());
        }
    }
}
