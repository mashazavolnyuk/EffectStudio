package com.example.darkmaleficent.effectstudio.effect;

/**
 * Created by Dark Maleficent on 17.06.2016.
 */
public abstract class Effect {

    int id;
    int image;
    String name;

    public Effect(){

    }

    public Effect(int id, String name, int image){
        this.id = id;
        this.name = name;
        this.image = image;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
