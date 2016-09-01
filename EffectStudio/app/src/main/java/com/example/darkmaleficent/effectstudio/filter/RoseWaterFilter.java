package com.example.darkmaleficent.effectstudio.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;

import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.effect.ISingleImageEffect;

/**
 * Created by Dark Maleficent on 01.09.2016.
 */
public class RoseWaterFilter extends TintFilter implements ISingleImageEffect {
    private int id;
    Context context;

    public RoseWaterFilter() {
        super(Filter.RoseWater, "Rose Water",0);
        id=Filter.RoseWater;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
        this.context = context;
        int color1 = ContextCompat.getColor(context, R.color.RoseWater);
        Bitmap bmp = addTint(bitmap, color1,TintFilter.Darken);
        return bmp;
    }
}