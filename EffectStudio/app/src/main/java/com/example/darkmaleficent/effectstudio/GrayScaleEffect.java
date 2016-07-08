package com.example.darkmaleficent.effectstudio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by Dark Maleficent on 17.06.2016.
 */
public class GrayScaleEffect extends SingleImageEffect {

    public GrayScaleEffect() {
        super(1, "GrayScale", R.mipmap.ic_add_a_photo_white_36dp,TypeEffect.Filter);
    }

    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
        Bitmap timebimp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        int red = 0;
        int blue = 0;
        int green = 0;
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                int colour = bitmap.getPixel(i, j);
                red = Color.red(colour);
                blue = Color.blue(colour);
                green = Color.green(colour);
                int newcolor = (red + blue + green) / 3;
                timebimp.setPixel(i, j, Color.rgb(newcolor, newcolor, newcolor));
            }
        }
        return timebimp;
    }
}
