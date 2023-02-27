package model.pokemon;

import model.Pokemon;
import model.attacks.Gust;
import model.attacks.Tackle;

import java.util.ArrayList;
import java.util.Arrays;

public class Pidgey extends Pokemon {
    public Pidgey(int level) {
        super("Pidgey", level);
        this.attacks = new ArrayList<>(Arrays.asList(new Tackle(), new Gust()));
    }

    @Override
    public void setHP() {
        this.hp = 40 * this.level;
    }
}
