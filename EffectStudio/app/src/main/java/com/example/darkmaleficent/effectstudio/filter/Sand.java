package com.example.darkmaleficent.effectstudio.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v4.content.ContextCompat;

import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.effect.ISingleImageEffect;

/**
 * Created by Dark Maleficent on 13.08.2016.
 */
public class Sand extends TintFilter implements ISingleImageEffect{

    int id;
    Context context;

    public Sand() {
        super(Filter.SAND, "Sand", R.mipmap.ic_mode_edit_white_48dp);
        id = Filter.SAND;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
        this.context = context;
        int color1 = ContextCompat.getColor(context, R.color.Sand);
        Bitmap bmp = addTint(bitmap, color1,0);
        return bmp;
    }


    private Bitmap addGradient(Bitmap src, int color1, int color2) {
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Bitmap mask = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_palm);
        canvas.drawBitmap(src, 0, 0, null);
        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, 0, 0, h, color1, color2, Shader.TileMode.CLAMP);

        paint.setColorFilter(new LightingColorFilter(color1,1));//add tint
       // paint.setShader(shader);
      // paint.setAlpha(255);
       //paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        canvas.drawBitmap(src,0, 0, paint);
        Paint paint1 = new Paint();
//        paint1.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
//        canvas.drawBitmap(mask, -2, 0, paint1);

        return result;
    }
}
