package model.pokemon;

import model.Pokemon;
import model.attacks.Astonish;
import model.attacks.Scratch;

import java.util.ArrayList;
import java.util.Arrays;

public class Diglett extends Pokemon {
    public Diglett(int level) {
        super("Diglett", level);
        this.attacks = new ArrayList<>(Arrays.asList(new Scratch(), new Astonish()));
    }

    @Override
    public void setHP() {
        this.hp = 10 * this.level;
    }
}
