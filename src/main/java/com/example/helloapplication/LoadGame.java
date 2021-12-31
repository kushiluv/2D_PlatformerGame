package com.example.helloapplication;

import java.io.File;
import java.util.ArrayList;

public class LoadGame {
    private ArrayList<String> Games;
    public LoadGame(){
        Games =  new ArrayList<>();
    }

    public ArrayList<String> getGames() {
        File [] source = new File("src/main/resources/LoadGames").listFiles();
        for(File f : source){
            Games.add(f.getName());
        }
        return Games;
    }
}
