package model;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Attack {

    private ArrayList<String> attacks;
//    private ArrayList<String> types;
    private ArrayList<Integer> powers;
//    private ArrayList<Double> accuracies;

    private String name;
    // protected String type;
    protected int power;
    // protected double acc;

//    public void setTypes() {
//        types = new ArrayList<>(Arrays.asList("Normal", "Normal", "Normal", "Normal", "Normal", "Normal", "Normal",
//                "Grass", "Fire", "Water", "Bug", "Flying", "Flying", "Dark", "Dark", "Electric", "Ghost", "Psychic",
//                "Rock", "Rock"));
//    }

//    public void setAccuracies() {
//        accuracies = new ArrayList<>(Arrays.asList(1.00, 1.00, 1.00, 1.00, 1.00, 0.85, 0.15, 1.00,
//                1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 0.90, 1.00));
//    }

    public Attack(String name) {

        this.name = name;
//        this.type = getType();
//        this.acc = getAcc();
    }

    public String getName() {
        return this.name;
    }

    public int getPower() {
        return this.power;
    }

//    public String getType() {
//        return types.get(attacks.indexOf(this.name));
//    }

//    public double getAcc() {
//        return accuracies.get(attacks.indexOf(this.name));
//    }
}