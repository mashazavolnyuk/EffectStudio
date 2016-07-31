package com.example.darkmaleficent.effectstudio;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public abstract class ChangeImage {
    int id;
    int image;
    String name;


    public ChangeImage(int id, String name, int image){
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

