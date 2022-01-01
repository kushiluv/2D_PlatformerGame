package com.example.helloapplication;

import java.io.*;
import java.util.ArrayList;

public class SaveGame implements serializable {

    private final File [] file;

//    private static ArrayList<Object> save_objs;


    public SaveGame(Serialized_obj o){
        file = new File("src/main/resources/LoadGames").listFiles();
        String name = "Load" + (file.length + 1) + ".txt";
        save(name, o);
    }

    public void save(String name, Serialized_obj o){
        ObjectOutputStream ob;
        try {
            ob = new ObjectOutputStream(new FileOutputStream("src/main/resources/LoadGames/"+name));
            ob.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

}

//class Serialized_obj{
//
//}
