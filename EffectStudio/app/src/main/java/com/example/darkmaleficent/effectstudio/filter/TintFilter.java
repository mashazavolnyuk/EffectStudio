package com.example.darkmaleficent.effectstudio.filter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

/**
 * Created by Dark Maleficent on 01.09.2016.
 */
public abstract class TintFilter extends Filter {

    static final int Screen = 1;
    static final int Lighten = 2;
    static final int Darken = 3;
    static final int Multiply = 4;
    Paint paint;


    public TintFilter(int id, String name) {
        super(id, name);

    }

    protected Bitmap addTint(Bitmap src, int color, int Xfermode) {
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(src, 0, 0, null);
        paint = new Paint();
        paint.setColorFilter(new LightingColorFilter(color, 1));//add tint
        switch (Xfermode) {
            case TintFilter.Screen:
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
                break;
            case TintFilter.Lighten:
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
                break;
            case TintFilter.Darken:
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));
                break;
            case TintFilter.Multiply:
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
                break;

        }
        canvas.drawBitmap(src, 0, 0, paint);
        return result;
    }

    protected Bitmap addColorMatrix(Bitmap src, float[] colorMaxtrix) {
        ColorMatrix colorMatrix = new ColorMatrix(colorMaxtrix);
        ColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        paint=new Paint();
        paint.setColorFilter(colorFilter);
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(src, 0, 0, paint);
        return result;
    }


}
