package com.mashazavolnyuk.effectstudio.gradient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;

import com.mashazavolnyuk.effectstudio.ChangeImage;
import com.mashazavolnyuk.effectstudio.MainActivity;
import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.interfaces.ISimpleChangeImage;

/**
 * Created by Dark Maleficent on 07.09.2016.
 */
public abstract class Gradient extends ChangeImage implements ISimpleChangeImage {

    Context context = MainActivity.getContext();
    private Bitmap originalPreview = BitmapFactory.decodeResource(context.getResources(), R.mipmap.preview);
    Bitmap mutableBitmap = originalPreview.copy(Bitmap.Config.ARGB_8888, true);
    private Bitmap preview;

    public Gradient(int id, String name) {
        super(id, name);
    }
    static final int PacificOcean=1;
    static final int Fuchsia=2;
    static final int MintTea=3;

    public abstract int getId();

    public Bitmap getPreview() {

        return preview = apply(mutableBitmap);
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
//        BitmapShader shader = new BitmapShader(mask,
//                Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        paint.setShader(new LinearGradient(0, 0, 0, h, color1, color2, Shader.TileMode.CLAMP));
        canvas.drawBitmap(src, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        canvas.drawRect(0, 0, w, h, paint);

//          Paint paint1 = new Paint();
//        paint1.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
//        canvas.drawBitmap(mask, -2, 0, paint1);

        return result;
    }
//    protected Bitmap addGradient(Bitmap src, int color1, int color2) {
//        int w = src.getWidth();
//        int h = src.getHeight();
//        Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(result);
//        //Bitmap mask = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_palm);
//        canvas.drawBitmap(src, 0, 0, null);
//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//
//        LinearGradient shader = new LinearGradient(0, 0, 0, h, color1, color2, Shader.TileMode.CLAMP);
//       // paint.setColorFilter(new LightingColorFilter(color1,1));//add tint
//        paint.setShader(shader);
//      // paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.));
//        canvas.drawBitmap(src,0, 0, paint);
//        Log.d("LinearGradient","756");
//      //  Paint paint1 = new Paint();
////        paint1.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
////        canvas.drawBitmap(mask, -2, 0, paint1);
//
//        return result;
//    }


}
