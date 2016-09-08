package com.example.darkmaleficent.effectstudio.gradient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.darkmaleficent.effectstudio.ChangeImage;
import com.example.darkmaleficent.effectstudio.MainActivity;
import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.interfaces.ISimpleChangeImage;

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

    public abstract int getId();

    public Bitmap getPreview() {
        return preview = apply(mutableBitmap);
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
