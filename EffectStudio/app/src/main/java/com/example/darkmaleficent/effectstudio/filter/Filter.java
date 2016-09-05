package com.example.darkmaleficent.effectstudio.filter;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.darkmaleficent.effectstudio.ChangeImage;
import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.effect.ISingleImageEffect;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public abstract class Filter extends ChangeImage implements ISingleImageEffect {

    private Bitmap originalPreview;
    protected Bitmap preview;

    static final int AquaFilter = 1;
    static final int DescreaseColor = 2;
    static final int HotSun = 3;
    static final int Grass = 4;
    static final int SAND = 5;
    static final int RoseWater=6;
    static final int Mysticism=7;
    static final int Gray=8;

    public abstract int getId();

    public Filter(int id, String name) {
        super(id, name);
        originalPreview= BitmapFactory.decodeResource(Resources.getSystem(), R.mipmap.preview);
        preview=apply(originalPreview);
    }
    public Bitmap getPreview() {
        return preview;
    }
}
