package com.mashazavolnyuk.effectstudio;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public abstract class ImageChanger {
    int id;
    String name;

    public ImageChanger(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {


        return name  = name.replace(" ","\n");
    }

    public void setName(String name) {
        this.name = name;
    }
}

