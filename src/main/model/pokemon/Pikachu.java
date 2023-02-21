package model.pokemon;

import model.Pokemon;
import model.attacks.QuickAttack;
import model.attacks.ThunderShock;

import java.util.ArrayList;
import java.util.Arrays;

public class Pikachu extends Pokemon {
    public Pikachu(int level) {
        super("Pikachu", level);
    }

    @Override
    public void setAttacks() {
        this.attacks = new ArrayList<>(Arrays.asList(new QuickAttack(), new ThunderShock()));
    }

    @Override
    public void setHP() {
        this.hp = 35 * this.level;
    }

//    @Override
//    public void setSpeed() {
//
//    }
}
