package com.example.darkmaleficent.effectstudio.effect;

import android.graphics.Bitmap;

/**
 * Created by Dark Maleficent on 17.06.2016.
 */
public class BlurPixelEffect extends Effect {


    public BlurPixelEffect() {
        super(3, "Blur Pixels");



    }

    @Override
    public Bitmap apply(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap small = Bitmap.createScaledBitmap(bitmap, 10, 10, true);
        return Bitmap.createScaledBitmap(small, width, height, true);
    }


}
