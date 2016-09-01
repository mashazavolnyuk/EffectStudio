package com.example.darkmaleficent.effectstudio.filter;

import com.example.darkmaleficent.effectstudio.ChangeImage;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public abstract class Filter extends ChangeImage {
    static final int AquaFilter = 1;
    static final int DescreaseColor = 2;
    static final int HotSun = 3;
    static final int Grass = 4;
    static final int SAND = 5;
    static final int RoseWater=6;
    static final int Mysticism=7;
    static final int Gray=8;

    public abstract int getId();

    public Filter(int id, String name, int image) {
        super(id, name, image);
    }
}
