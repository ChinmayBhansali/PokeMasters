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
    private Attack a21;
    private Attack a22;
    private Attack a23;

    private ArrayList<Attack> attacks;
    private ArrayList<String> attackNames;
    private ArrayList<Integer> attackPowers;

    @BeforeEach
    void setup() {
        a1 = new AncientPower();
        a2 = new Assurance();
        a3 = new Astonish();
        a4 = new Bind();
        a5 = new Bite();
        a6 = new BugBite();
        a7 = new Confusion();
        a8 = new Ember();
        a9 = new FakeOut();
        a10 = new Feint();
        a11 = new Gust();
        a12 = new Peck();
        a13 = new PsychoCut();
        a14 = new QuickAttack();
        a15 = new RapidSpin();
        a16 = new RockThrow();
        a17 = new Scratch();
        a18 = new SmackDown();
        a19 = new Swift();
        a20 = new Tackle();
        a21 = new ThunderShock();
        a22 = new VineWhip();
        a23 = new WaterGun();

        attacks = new ArrayList<>(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16,
                a17, a18, a19, a20, a21, a22, a23));
        attackNames = new ArrayList<>(Arrays.asList("Ancient Power", "Assurance", "Astonish", "Bind", "Bite",
                "Bug Bite", "Confusion", "Ember", "Fake Out", "Feint", "Gust", "Peck", "Psycho Cut", "Quick Attack",
                "Rapid Spin", "Rock Throw", "Scratch", "Smack Down", "Swift", "Tackle", "Thunder Shock", "Vine Whip",
                "Water Gun"));
        attackPowers = new ArrayList<>(Arrays.asList(60, 60, 30, 15, 60, 60, 50, 40, 40, 30, 40, 35, 70, 40, 50, 50, 40,
                50, 60, 40, 40, 45, 40));
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