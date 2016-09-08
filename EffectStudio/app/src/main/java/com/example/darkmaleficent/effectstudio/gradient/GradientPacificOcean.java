package com.example.darkmaleficent.effectstudio.gradient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.v4.content.ContextCompat;

import com.example.darkmaleficent.effectstudio.MainActivity;
import com.example.darkmaleficent.effectstudio.R;

/**
 * Created by Dark Maleficent on 07.09.2016.
 */
public class GradientPacificOcean extends Gradient {
    private int id;

    public GradientPacificOcean() {
        super(Gradient.PacificOcean, "Pacific Ocean");
        id = Gradient.PacificOcean;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Bitmap apply(Bitmap bitmap) {
        int color1 = ContextCompat.getColor(MainActivity.getContext(), R.color.Sand);
        int color2 = ContextCompat.getColor(MainActivity.getContext(), R.color.Aqua);
        Bitmap bmp = addGradient(bitmap, color1, color2);
        return bmp;
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
         paint.setAlpha(190);
        //bitmap = BitmapFactory.decodeResource(getResources(),
              //  R.drawable.ic_launcher);
        BitmapShader shader = new BitmapShader(mask,
                Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
      //  Shader shader = new LinearGradient(0, 0, 0, h, color1, color2, Shader.TileMode.CLAMP);
        // paint.setColorFilter(new LightingColorFilter(color1,1));//add tint
        paint.setShader(new LinearGradient(0, 0, 0, h, color1, color2, Shader.TileMode.CLAMP));
        canvas.drawBitmap(src, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        canvas.drawRect(0, 0, w, h, paint);

//          Paint paint1 = new Paint();
//        paint1.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
//        canvas.drawBitmap(mask, -2, 0, paint1);

        return result;
    }
}
