package com.example.helloapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class Serialized_obj implements Serializable {
    private static final long serialVersionUID = 20L;

  private ArrayList<Coordinate> coordinates;
    private int coinc[];
    private int dashc[];
    private int knife;
    private int knife1;
    private int axe;
    private boolean a;
    private boolean s;
    private boolean d;


    public Serialized_obj( int[] coinc, int[] dashc, ArrayList<Coordinate> c, int k, int k1, int ax, boolean a, boolean s, boolean d) {

        this.coinc = coinc;
        this.dashc = dashc;
        this.coordinates = c;
        this.knife = k;
        this.knife1 = k1;
        this.axe = ax;
        this.a = a;
        this.s = s;
        this.d = d;

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

    public int getKnife() {
        return knife;
    }

    public int getKnife1() {
        return knife1;
    }

    public int getAxe() {
        return axe;
    }

    public boolean isA() {
        return a;
    }

    public boolean isS() {
        return s;
    }

    public boolean isD() {
        return d;
    }

    public void setCoordinates(ArrayList<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public void setCoinc(int[] coinc) {
        this.coinc = coinc;
    }

    public void setDashc(int[] dashc) {
        this.dashc = dashc;
    }

    public void setKnife(int knife) {
        this.knife = knife;
    }

    public void setKnife1(int knife1) {
        this.knife1 = knife1;
    }

    public void setAxe(int axe) {
        this.axe = axe;
    }

    public void setA(boolean a) {
        this.a = a;
    }

    public void setS(boolean s) {
        this.s = s;
    }

    public void setD(boolean d) {
        this.d = d;
    }
}
