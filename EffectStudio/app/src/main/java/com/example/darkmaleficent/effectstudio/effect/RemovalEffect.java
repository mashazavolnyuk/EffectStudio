package com.example.darkmaleficent.effectstudio.effect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;

import com.example.darkmaleficent.effectstudio.R;

/**
 * Created by Dark Maleficent on 29.06.2016.
 */
public class RemovalEffect extends SingleImageEffect {

    public RemovalEffect() {
        super(5, "RemovalEffect", R.mipmap.ic_add_a_photo_white_36dp);
    }

    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
        Bitmap outputImage = doBrightness(bitmap,50);
        return outputImage;
    }

    public Bitmap doBrightness(Bitmap originalImage, int value) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        Bitmap bmOut = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int A, R, G, B;
        int pixel;
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                pixel = originalImage.getPixel(x, y);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);

                if (R > 255) {
                    R = 255;
                } else if (R < 0) {
                    R = 0;
                }
                R += value;

                if (G > 255) {
                    G = 255;
                } else if (G < 0) {
                    G = 0;
                }
                G += value;
                B += value;
                if (B > 255) {
                    B = 255;
                } else if (B < 0) {
                    B = 0;
                }
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
        return bmOut;
    }
}
