package com.example.darkmaleficent.effectstudio.filter;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.darkmaleficent.effectstudio.effect.ISingleImageEffect;

/**
 * Created by Dark Maleficent on 01.09.2016.
 */
public class GrayFilter extends TintFilter implements ISingleImageEffect {

   private float[] cmData = new float[]{
            0.213f, 0.715f, 0.072f, 0.0f, 0.0f,
            0.213f, 0.715f, 0.072f, 0.0f, 0.0f,
            0.213f, 0.715f, 0.072f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f};

    public GrayFilter() {
        super(Filter.Gray, "Gray", 0);
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
        Bitmap bmp=addColorMatrix(bitmap,cmData);
        return bmp;
    }
}
