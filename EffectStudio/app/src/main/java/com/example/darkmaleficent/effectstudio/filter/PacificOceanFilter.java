package com.example.darkmaleficent.effectstudio.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.v4.content.ContextCompat;

import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.effect.ISingleImageEffect;

/**
 * Created by Dark Maleficent on 13.08.2016.
 */
public class PacificOceanFilter extends Filter implements ISingleImageEffect {

    int id;
    Context context;
    public PacificOceanFilter() {
        super(Filter.PacificOcean, "PacificOcean", R.mipmap.ic_mode_edit_white_48dp);
        id=Filter.PacificOcean;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
        this.context=context;
        int color1 = ContextCompat.getColor(context, R.color.Aqua);
        int color2= ContextCompat.getColor(context, R.color.BlueSea);
        Bitmap bmp=addGradient(bitmap, color1,color2);
        return bmp;

    }


    public Bitmap addGradient(Bitmap src, int color1, int color2)
    {
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap result = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Bitmap mask= BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_palm);
       canvas.drawBitmap(src, 0, 0, null);
        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0,0,0,h, color1, color2, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAlpha(60);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        canvas.drawRect(0,0,w,h,paint);
        Paint paint1 = new Paint();
        paint1.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        canvas.drawBitmap(mask,-2,0,paint1);

        return result;
    }
}
