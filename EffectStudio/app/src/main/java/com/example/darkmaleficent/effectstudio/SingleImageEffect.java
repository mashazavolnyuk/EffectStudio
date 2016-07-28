package com.example.darkmaleficent.effectstudio;

/**
 * Created by Dark Maleficent on 17.06.2016.
 */
public abstract class SingleImageEffect extends Effect implements ISingleImageEffect{

    public SingleImageEffect(int id, String name, int image) {
        super(id, name, image);
    }
}
