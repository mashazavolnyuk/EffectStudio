package com.example.darkmaleficent.effectstudio.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.graphics.Palette;

import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.effect.ISingleImageEffect;
import com.mukesh.image_processing.ImageProcessor;

/**
 * Created by Dark Maleficent on 13.08.2016.
 */
public class DecreaseFilter extends Filter implements ISingleImageEffect {
int id;

    public DecreaseFilter() {
        super(Filter.DescreaseColor, "Decrease", R.mipmap.ic_mode_edit_white_48dp);
        id=Filter.DescreaseColor;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
        ImageProcessor processor=new ImageProcessor();
        //Bitmap bmp=processor.decreaseColorDepth(bitmap,100);
        Palette palette= Palette.from(bitmap).generate();
        int def = 0x000000;
        int vibrant = palette.getVibrantColor(def);
        int vibrantLight = palette.getDarkMutedColor(def);
//        Bitmap bitmap1=processor.doHighlightImage(bitmap,140,vibrantLight);
//        Bitmap bmp=processor.doHighlightImage(bitmap1,120,vibrant);
        //Bitmap bmp=processor.doColorFilter(bitmap,1,1,0.2);//+

        //Recept 1 "&"
//        Bitmap bmp=processor.doColorFilter(bitmap,1,1,0.8);
//        Bitmap r=processor.doGamma(bmp,1,0.5,0.8);

        //Recept 2 "Purple Night"
        //Bitmap r=changeBlue(bitmap,20);

        //Recept 3 "Sunset"
//        Bitmap r=processor.doGamma(bitmap,4,1,1);
//        return r;

        //Recept 4 ""
        Bitmap r=processor.doGamma(bitmap,4,1,1);
        return r;


    }

    private Bitmap changeBlue(Bitmap originalImage,double blue) {
        Bitmap bmOut = Bitmap.createBitmap(originalImage.getWidth(), originalImage.getHeight(),
                originalImage.getConfig());
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int A, R, G, B;
        int pixel;
        final int MAX_SIZE = 256;
        final double MAX_VALUE_DBL = 255.0;
        final int MAX_VALUE_INT = 255;
        final double REVERSE = 1.0;
        int[] gammaB = new int[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; ++i) {
            gammaB[i] = (int) Math.min(MAX_VALUE_INT,
                    (int) ((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / blue)) + 0.5));
        }
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                pixel = originalImage.getPixel(x, y);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = gammaB[Color.blue(pixel)];
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
        return bmOut;
    }
}
