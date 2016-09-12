package com.mashazavolnyuk.effectstudio.gradient;

import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;

import com.mashazavolnyuk.effectstudio.MainActivity;
import com.mashazavolnyuk.effectstudio.R;

/**
 * Created by Dark Maleficent on 12.09.2016.
 */
public class GradientCherryBlossoms extends Gradient {

    int id;
    public GradientCherryBlossoms() {
        super(Gradient.CherryBlossoms, "Cherry blossoms");
        id=CherryBlossoms;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Bitmap apply(Bitmap bitmap) {
        int color1 = ContextCompat.getColor(MainActivity.getContext(), R.color.PinkCherry);
        int color2 = ContextCompat.getColor(MainActivity.getContext(), R.color.PalePink);
        Bitmap bmp = addGradient(bitmap, color1, color2,Gradient.TYPE_GRADIENT_LINEAR);
        return bmp;
    }
}
