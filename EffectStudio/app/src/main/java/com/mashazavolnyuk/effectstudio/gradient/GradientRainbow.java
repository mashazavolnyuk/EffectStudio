package com.mashazavolnyuk.effectstudio.gradient;

import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;

import com.mashazavolnyuk.effectstudio.MainActivity;
import com.mashazavolnyuk.effectstudio.R;

/**
 * Created by Dark Maleficent on 17.10.2016.
 */

public class GradientRainbow extends Gradient {

    public GradientRainbow() {
        super("Rainbow");
    }

    @Override
    public Bitmap apply(Bitmap bitmap) {
        int color1 = ContextCompat.getColor(MainActivity.getContext(),R.color.Fuchsia);
        int color2 = ContextCompat.getColor(MainActivity.getContext(), R.color.Appricot);
        int color3=ContextCompat.getColor(MainActivity.getContext(),R.color.lemon);
        int color4=ContextCompat.getColor(MainActivity.getContext(),R.color.Lime);
        int color5=ContextCompat.getColor(MainActivity.getContext(),R.color.BlueSea);
        int color6=ContextCompat.getColor(MainActivity.getContext(),R.color.Navy);
        int color7=ContextCompat.getColor(MainActivity.getContext(),R.color.BlueViolet);
        int []colors={color1,color2,color3,color4,color5,color6,color7};
        Bitmap bmp = addGradient(bitmap,colors,Gradient.TYPE_GRADIENT_LINEAR);
        return bmp;
    }
}
