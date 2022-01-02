package com.example.helloapplication;

import java.io.Serializable;

public class Coordinate implements Serializable {
    private int layoutx;
    private int layouty;

    public void setLayoutx(int layoutx) {
        this.layoutx = layoutx;
    }

    public void setLayouty(int layouty) {
        this.layouty = layouty;
    }

    public int getLayoutx() {
        return layoutx;
    }

    public int getLayouty() {
        return layouty;
    }
}
