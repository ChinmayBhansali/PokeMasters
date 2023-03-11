package model.pokemon;

import model.Pokemon;
import model.attacks.Confusion;
import model.attacks.AncientPower;
import model.attacks.PsychoCut;
import model.attacks.Swift;

import java.util.ArrayList;
import java.util.Arrays;

public class Mewtwo extends Pokemon {
    public Mewtwo(int level) {
        super("Mewtwo", level);
    }

    @Override
    public void setHP() {
        hp = 106 * level;
    }

    @Override
    public void setAttacks() {
        attacks = new ArrayList<>(Arrays.asList(new Confusion(), new Swift()));
        learnAttacks();
    }

    private void learnAttacks() {
        if (level >= 8) {
            attacks.add(new AncientPower());
        }
        if (level >= 16) {
            attacks.add(new PsychoCut());
        }
    }
}
