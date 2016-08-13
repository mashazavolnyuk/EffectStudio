package com.example.darkmaleficent.effectstudio.filter;

import com.example.darkmaleficent.effectstudio.ChangeImage;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public abstract class Filter extends ChangeImage {
    static final int AquaFilter=1;
    static final int DescreaseColor=2;
    static final int HotSun =3;
    static final int Grass =4;
    static final int PacificOcean=6;

    public abstract int getId();
    public Filter(int id, String name, int image) {
        super(id, name, image);
    }
}
