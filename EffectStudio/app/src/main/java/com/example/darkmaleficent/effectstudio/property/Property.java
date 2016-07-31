package com.example.darkmaleficent.effectstudio.property;

import com.example.darkmaleficent.effectstudio.ChangeImage;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public abstract class Property extends ChangeImage {

    static final int Brightness=1;
    static final int Contrast=1;

    public Property(int id, String name, int image) {
        super(id, name, image);
    }
}
