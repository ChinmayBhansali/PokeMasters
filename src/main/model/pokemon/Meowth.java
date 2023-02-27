package model.pokemon;

import model.Pokemon;
import model.attacks.FakeOut;
import model.attacks.Feint;
import model.attacks.Scratch;

import java.util.ArrayList;
import java.util.Arrays;

public class Meowth extends Pokemon {
    public Meowth(int level) {
        super("Meowth", level);
        this.attacks = new ArrayList<>(Arrays.asList(new FakeOut(), new Feint(), new Scratch()));
    }

    @Override
    public void setHP() {
        this.hp = 40 * this.level;
    }
}
