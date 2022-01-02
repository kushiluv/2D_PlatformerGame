package com.example.helloapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class Serialized_obj implements Serializable {
    private static final long serialVersionUID = 20L;

  private ArrayList<Coordinate> coordinates;
    private int coinc[];
    private int dashc[];

    public Serialized_obj( int[] coinc, int[] dashc, ArrayList<Coordinate> c) {

        this.coinc = coinc;
        this.dashc = dashc;
        this.coordinates = c;

    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public int[] getCoinc() {
        return coinc;
    }

    public int[] getDashc() {
        return dashc;
    }
}
