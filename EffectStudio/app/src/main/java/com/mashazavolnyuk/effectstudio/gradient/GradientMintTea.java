package com.mashazavolnyuk.effectstudio.gradient;

import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;

import com.mashazavolnyuk.effectstudio.MainActivity;
import com.mashazavolnyuk.effectstudio.R;

/**
 * Created by Dark Maleficent on 08.09.2016.
 */
public class GradientMintTea extends Gradient {
    int id;

    public GradientMintTea() {
        super(Gradient.MINT_TEA, "Tea with Mint");
        id= MINT_TEA;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Bitmap apply(Bitmap bitmap) {
        int color1 = ContextCompat.getColor(MainActivity.getContext(), R.color.Mint);
        int color2 = ContextCompat.getColor(MainActivity.getContext(), R.color.Cacao);
        Bitmap bmp = addGradient(bitmap, color1, color2,Gradient.TYPE_GRADIENT_LINEAR);
        return bmp;
    }
}
