package com.example.helloapplication;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LoadGame {
    private ArrayList<String> Games;
    private Serialized_obj instance;
    public LoadGame(){
        Games =  new ArrayList<>();
    }

    public Serialized_obj Load(String s){
        String path = "src/main/resources/LoadGames/" + s;
        try {
            ObjectInputStream ou = new ObjectInputStream(new FileInputStream(path));
            instance = (Serialized_obj) ou.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public ArrayList<String> getGames() {
        File [] source = new File("src/main/resources/LoadGames").listFiles();
        for(File f : source){
            Games.add(f.getName());
        }
        return Games;
    }
}
