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
        this.attacks = new ArrayList<>(Arrays.asList(new Confusion(), new Swift(), new AncientPower(),
                new PsychoCut()));
    }

    @Override
    public void setHP() {
        this.hp = 106 * this.level;
    }
}
