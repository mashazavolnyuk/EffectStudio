package com.mashazavolnyuk.effectstudio.gradient;

import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;

import com.mashazavolnyuk.effectstudio.MainActivity;
import com.mashazavolnyuk.effectstudio.R;

/**
 * Created by Dark Maleficent on 12.09.2016.
 */
public class GradientGreenDay extends Gradient {

    int id;

    public GradientGreenDay() {
        super(Gradient.GREEN_DAY, "Green Day");
        id= GREEN_DAY;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Bitmap apply(Bitmap bitmap) {
        int color1 = ContextCompat.getColor(MainActivity.getContext(), R.color.Mint);
        int color2 = ContextCompat.getColor(MainActivity.getContext(), R.color.GreenField);
        int color4=ContextCompat.getColor(MainActivity.getContext(),R.color.mainBackground);
        int []colors={color1,color4,color2};
        Bitmap bmp = addGradient(bitmap,colors,Gradient.TYPE_GRADIENT_SWEEP);
        return bmp;
    }
}
