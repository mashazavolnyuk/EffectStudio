package com.example.darkmaleficent.effectstudio.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.support.v7.graphics.Palette;

import com.example.darkmaleficent.effectstudio.R;
import com.mukesh.image_processing.ImageProcessor;

/**
 * Created by Dark Maleficent on 13.08.2016.
 */
public class DecreaseFilter extends Filter  {
    int id;
    Context context;

    public DecreaseFilter() {
        super(Filter.DescreaseColor, "Vibrant");
        id = Filter.DescreaseColor;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Bitmap apply(Bitmap bitmap) {
        this.context = context;
        ImageProcessor processor = new ImageProcessor();
        //Bitmap bmp=processor.decreaseColorDepth(bitmap,100);
        Palette palette = Palette.from(bitmap).generate();
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


        //new Effect
//        Bitmap bmp = processor.engrave(bitmap);
//        return bmp;

        //new Effect
        Bitmap bmp = changeBlue(bitmap, vibrant);
        return bmp;


    }

    private Bitmap changeBlue(Bitmap originalImage, double blue) {
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
                B = Color.blue(pixel);

                int r = (Color.red(com.example.darkmaleficent.effectstudio.R.color.Aqua));
                int g = (Color.green(com.example.darkmaleficent.effectstudio.R.color.Aqua));
                int b = Color.blue(com.example.darkmaleficent.effectstudio.R.color.Aqua);
                bmOut.setPixel(x, y, Color.argb(A, R, G, 190));

            }
        }
        return bmOut;
    }


    private Bitmap changeBitmapColor(Bitmap sourceBitmap, int color) {
        Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap, 0, 0,
                sourceBitmap.getWidth() - 1, sourceBitmap.getHeight() - 1);
        Paint p = new Paint();
        ColorFilter filter = new LightingColorFilter(color, 1);
        int blueModify = Color.blue(com.example.darkmaleficent.effectstudio.R.color.Aqua);
        int red = Color.red(R.color.BlueSea);
        ColorFilter filter1 = new LightingColorFilter(blueModify, 1);
        p.setAlpha(100);
        p.setColorFilter(filter1);
        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(resultBitmap, 0, 0, p);
        return resultBitmap;
    }

    public int mixColors(int col1, int col2) {
        int r1, g1, b1, r2, g2, b2;

        r1 = Color.red(col1);
        g1 = Color.green(col1);
        b1 = Color.blue(col1);

        r2 = Color.red(col2);
        g2 = Color.green(col2);
        b2 = Color.blue(col2);

        int r3 = (r1 + r2) / 2;
        int g3 = (g1 + g2) / 2;
        int b3 = (b1 + b2) / 2;

        return Color.rgb(r3, g3, b3);


    }
}
