package com.example.helloapplication;

import java.io.Serializable;

public class Serialized_obj implements Serializable {
    private static final long serialVersionUID = 20L;

    private Hero hero;
    private int coinc[];
    private int dashc[];

    public Serialized_obj(Hero hero, int[] coinc, int[] dashc) {
        this.hero = hero;
        this.coinc = coinc;
        this.dashc = dashc;
    }

    public Hero getHHero() {
        return hero;
    }

    public int[] getCoinc() {
        return coinc;
    }

    public int[] getDashc() {
        return dashc;
    }
}
