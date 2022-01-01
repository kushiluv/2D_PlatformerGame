package com.example.helloapplication;

import java.io.*;
import java.util.ArrayList;

public class SaveGame implements Serializable {

    private final File [] file;
    private Serialized_obj object;
//    private static ArrayList<Object> save_objs;


    public SaveGame(Serialized_obj o){
        file = new File("src/main/resources/LoadGames").listFiles();
        System.out.println("asaaf");
        String name = "Load" + (file.length + 1) + ".txt";
        object = o;
        save(name, object);
    }

    public void save(String name, Serialized_obj o){
        ObjectOutputStream ob;
        try {
            System.out.println(name);
            ob = new ObjectOutputStream(new FileOutputStream("src/main/resources/LoadGames/"+name));
            ob.writeObject(o);
            ob.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

}

//class Serialized_obj{
//
//}
