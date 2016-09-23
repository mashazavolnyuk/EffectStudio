package com.mashazavolnyuk.effectstudio.gradient;

import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;

import com.mashazavolnyuk.effectstudio.MainActivity;
import com.mashazavolnyuk.effectstudio.R;

/**
 * Created by Dark Maleficent on 12.09.2016.
 */
public class GradientMiamiBeach extends Gradient {
    int id;
    public GradientMiamiBeach() {
        super(Gradient.MIAMI_BEACH, "Miami Beach");
        id= MIAMI_BEACH;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Bitmap apply(Bitmap bitmap) {
        int color1 = ContextCompat.getColor(MainActivity.getContext(), R.color.Appricot);
        int color2 = ContextCompat.getColor(MainActivity.getContext(), R.color.Lagoon);
        int color4=ContextCompat.getColor(MainActivity.getContext(),R.color.Lime);
        int []colors={color1,color2,color4};
        Bitmap bmp = addGradient(bitmap,colors,Gradient.TYPE_GRADIENT_LINEAR);
        //Bitmap bmp=addTexture(bitmap);
        return bmp;
    }
}
