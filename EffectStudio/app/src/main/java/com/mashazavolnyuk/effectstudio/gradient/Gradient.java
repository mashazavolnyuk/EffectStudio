package com.mashazavolnyuk.effectstudio.gradient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;

import com.mashazavolnyuk.effectstudio.ImageChanger;
import com.mashazavolnyuk.effectstudio.MainActivity;
import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.interfaces.ISimpleChangeImage;


public abstract class Gradient extends ImageChanger implements ISimpleChangeImage {

    Context context = MainActivity.getContext();
    private Bitmap originalPreview = BitmapFactory.decodeResource(context.getResources(), R.mipmap.preview);
    Bitmap mutableBitmap = originalPreview.copy(Bitmap.Config.ARGB_8888, true);
    private Bitmap preview;
    private int w;
    private int h;


    public Gradient(int id, String name) {
        super(id, name);
    }

    static final int PACIFIC_OCEAN = 1;
    static final int FUCHSIA = 2;
    static final int MINT_TEA = 3;
    static final int BARBIE_DOLL = 4;
    static final int GREEN_DAY =5;
    static final int MIAMI_BEACH =6;
    static final int CHERRY_BLOSSOMS =7;

    static final int TYPE_GRADIENT_LINEAR = 1;
    static final int TYPE_GRADIENT_RADIAL = 2;
    static final int TYPE_GRADIENT_SWEEP = 3;

    private int getW() {
        return w;
    }

    private int getH() {

        return h;
    }

    private void setW(int w) {
        this.w = w;
    }
    private void setH(int h){
        this.h=h;

    }

    public abstract int getId();

    public Bitmap getPreview() {

        return preview = apply(mutableBitmap);
    }

    protected Bitmap addGradient(Bitmap src, int color1, int color2, int TYPE_GRADIENT) {
        setW(src.getWidth());
        setH(src.getHeight());
        int[] colors = {color1, color2};
        Shader shader = getGradientShader(TYPE_GRADIENT, colors);
        Bitmap bmp = draw(src, shader);
        return bmp;
    }

    protected Bitmap addGradient(Bitmap src, int[] colors, int TYPE_GRADIENT) {
        setW(src.getWidth());
        setH(src.getHeight());
        Shader shader = getGradientShader(TYPE_GRADIENT, colors);
        Bitmap bmp = draw(src, shader);
        return bmp;
    }


    protected Bitmap addTexture(Bitmap src){
        setW(src.getWidth());
        setH(src.getHeight());
        Bitmap texture = BitmapFactory.decodeResource(MainActivity.getContext().getResources(),
                R.mipmap.star);
        BitmapShader shader = new BitmapShader(texture,
                Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        Bitmap result=draw(src,shader);
        return result;
    }

    private Bitmap draw(Bitmap src, Shader shader) {
        Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(src, 0, 0, null);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(190);
        paint.setShader(shader);
        canvas.drawBitmap(src, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));
        canvas.drawRect(0, 0, w, h, paint);
        return result;
    }
    private Shader getGradientShader(int TYPE_SHADER_GRADIENT, int colors[]) {
        Shader shader = new Shader();
        switch (TYPE_SHADER_GRADIENT) {
            case TYPE_GRADIENT_LINEAR:
                shader = new LinearGradient(0, 0, 0, h, colors,null,Shader.TileMode.CLAMP);
                break;
            case TYPE_GRADIENT_RADIAL:
                shader = new RadialGradient(getW()/2, getH()/2, getW()/3, colors, null, Shader.TileMode.CLAMP);
                break;
            case TYPE_GRADIENT_SWEEP:
                shader = new SweepGradient(getW(), getH(), colors, null);
                break;
        }
        return shader;
    }

}

