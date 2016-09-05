package com.example.darkmaleficent.effectstudio;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public abstract class ChangeImage {
    int id;
    String name;

    public ChangeImage(int id, String name){
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

