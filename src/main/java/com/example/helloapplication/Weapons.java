package com.example.helloapplication;

import javafx.scene.image.ImageView;

public interface Weapons {
    public ImageView getKnife();
    public void setEquippedTrue();
    public void setEquippedfalse();
    public boolean isEquipped();
    public int getUpgrade_level();
    public void setUpgrade_level(int i);
}
