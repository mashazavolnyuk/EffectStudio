package com.example.darkmaleficent.effectstudio.filter;

import android.graphics.Bitmap;

/**
 * Created by Dark Maleficent on 01.09.2016.
 */
public class GrayFilter extends TintFilter {

   private float[] cmData = new float[]{
            0.213f, 0.715f, 0.072f, 0.0f, 0.0f,
            0.213f, 0.715f, 0.072f, 0.0f, 0.0f,
            0.213f, 0.715f, 0.072f, 0.0f, 0.0f,
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f};

    public GrayFilter() {
        super(Filter.Gray, "Gray");
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public Bitmap apply(Bitmap bitmap) {
        Bitmap bmp=addColorMatrix(bitmap,cmData);
        return bmp;
    }
}
