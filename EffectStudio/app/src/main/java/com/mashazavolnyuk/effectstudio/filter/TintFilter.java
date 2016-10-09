package com.mashazavolnyuk.effectstudio.filter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;

import com.mashazavolnyuk.effectstudio.R;

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
        super( name);

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

    protected Bitmap addGradient(Bitmap src, int color1, int color2) {

        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Bitmap mask = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_palm);
        canvas.drawBitmap(src, 0, 0, null);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        //bitmap = BitmapFactory.decodeResource(getResources(),
        //  R.drawable.dots);

        BitmapShader shader = new BitmapShader(mask,
                Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        //  Shader shader = new LinearGradient(0, 0, 0, h, color1, color2, Shader.TileMode.CLAMP);
        // paint.setColorFilter(new LightingColorFilter(color1,1));//add tint
        paint.setShader(new LinearGradient(0, 0, 0, h, color1, color2, Shader.TileMode.CLAMP));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        canvas.drawBitmap(src, 0, 0, paint);

//          Paint paint1 = new Paint();
//        paint1.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
//        canvas.drawBitmap(mask, -2, 0, paint1);

        return result;
    }


}
