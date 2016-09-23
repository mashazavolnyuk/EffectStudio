package com.mashazavolnyuk.effectstudio.property;

import com.mashazavolnyuk.effectstudio.ImageChanger;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public abstract class Property extends ImageChanger {

    static final int Brightness=1;
    static final int Contrast=2;
    static final int Opacity=3;
    public abstract int getId();

    public Property(int id, String name, int image) {
        super(id, name);
    }
}
