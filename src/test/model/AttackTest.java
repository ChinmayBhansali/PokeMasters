package model;

import model.attacks.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AttackTest {
    private Attack a1;
    private Attack a2;
    private Attack a3;
    private Attack a4;
    private Attack a5;
    private Attack a6;
    private Attack a7;
    private Attack a8;
    private Attack a9;
    private Attack a10;
    private Attack a11;
    private Attack a12;
    private Attack a13;
    private Attack a14;
    private Attack a15;
    private Attack a16;
    private Attack a17;
    private Attack a18;
    private Attack a19;
    private Attack a20;

    private ArrayList<Attack> attacks;
    private ArrayList<String> attackNames;
    private ArrayList<Integer> attackPowers;

    @BeforeEach
    void setup() {
        a1 = new Assurance();
        a2 = new Astonish();
        a3 = new Bind();
        a4 = new Bite();
        a5 = new BugBite();
        a6 = new Confusion();
        a7 = new Ember();
        a8 = new FakeOut();
        a9 = new Feint();
        a10 = new Gust();
        a11 = new Peck();
        a12 = new QuickAttack();
        a13 = new RapidSpin();
        a14 = new RockThrow();
        a15 = new Scratch();
        a16 = new SmackDown();
        a17 = new Tackle();
        a18 = new ThunderShock();
        a19 = new VineWhip();
        a20 = new WaterGun();

        attacks = new ArrayList<>(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16,
                a17, a18, a19, a20));
        attackNames = new ArrayList<>(Arrays.asList("Assurance", "Astonish", "Bind", "Bite", "Bug Bite", "Confusion",
                "Ember", "Fake Out", "Feint", "Gust", "Peck", "Quick Attack", "Rapid Spin", "Rock Throw", "Scratch",
                "Smack Down", "Tackle", "Thunder Shock", "Vine Whip", "Water Gun"));
        attackPowers = new ArrayList<>(Arrays.asList(60, 30, 15, 60, 60, 50, 40, 40, 30, 40, 35, 40, 50, 50, 40, 50,
                40, 40, 45, 40));
    }

    @Test
    void testConstructor() {
        int i = 0;
        for (Attack a : attacks) {
            assertEquals(attackNames.get(i), a.getName());
            assertEquals(attackPowers.get(i), a.getPower());
            i++;
        }
    }
}