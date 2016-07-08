package com.example.darkmaleficent.effectstudio;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by Dark Maleficent on 17.06.2016.
 */
public class BlurPixelEffect extends SingleImageEffect {

    public BlurPixelEffect() {
        super(3, "Blur Pixels", R.mipmap.ic_add_a_photo_white_36dp,TypeEffect.Filter);
    }

    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap small = Bitmap.createScaledBitmap(bitmap, 10, 10, true);
        return Bitmap.createScaledBitmap(small, width, height, true);
    }
}
