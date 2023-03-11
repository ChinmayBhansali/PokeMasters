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
    private Attack a24;
    private Attack a25;

    private ArrayList<Attack> attacks;
    private ArrayList<String> attackNames;
    private ArrayList<Integer> attackPowers;

    @BeforeEach
    void setup() {
        a1 = new AerialAce();
        a2 = new AncientPower();
        a3 = new Assurance();
        a4 = new Astonish();
        a5 = new Bind();
        a6 = new Bite();
        a7 = new BugBite();
        a8 = new Confusion();
        a9 = new Ember();
        a10 = new FakeOut();
        a11 = new Feint();
        a12 = new Gust();
        a13 = new PayDay();
        a14 = new Peck();
        a15 = new PsychoCut();
        a16 = new QuickAttack();
        a17 = new RapidSpin();
        a18 = new RockThrow();
        a19 = new Scratch();
        a20 = new SmackDown();
        a21 = new Swift();
        a22 = new Tackle();
        a23 = new ThunderShock();
        a24 = new VineWhip();
        a25 = new WaterGun();

        attacks = new ArrayList<>(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16,
                a17, a18, a19, a20, a21, a22, a23, a24, a25));
        attackNames = new ArrayList<>(Arrays.asList("Aerial Ace", "Ancient Power", "Assurance", "Astonish", "Bind", "Bite",
                "Bug Bite", "Confusion", "Ember", "Fake Out", "Feint", "Gust", "Pay Day", "Peck", "Psycho Cut", "Quick Attack",
                "Rapid Spin", "Rock Throw", "Scratch", "Smack Down", "Swift", "Tackle", "Thunder Shock", "Vine Whip",
                "Water Gun"));
        attackPowers = new ArrayList<>(Arrays.asList(60, 60, 60, 30, 15, 60, 60, 50, 40, 40, 30, 40, 40, 35, 70, 40, 50, 50, 40,
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