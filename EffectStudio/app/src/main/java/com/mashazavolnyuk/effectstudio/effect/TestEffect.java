package com.mashazavolnyuk.effectstudio.effect;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.opengl.GLSurfaceView;

import com.mashazavolnyuk.effectstudio.R;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


/**
 * Created by Dark Maleficent on 28.07.2016.
 */
public class TestEffect extends Effect implements GLSurfaceView.Renderer {

    public TestEffect() {
        super("Test");
    }


    @Override
    public Bitmap apply(Bitmap bitmap) {
        Bitmap bitmap2 = BitmapFactory.decodeResource(Resources.getSystem(), R.mipmap.smurfs);
        Bitmap overlayBitmap = Bitmap.createBitmap(bitmap);
        Canvas canvas = new Canvas(overlayBitmap);
        canvas.setBitmap(overlayBitmap);
        canvas.drawBitmap(bitmap, new Matrix(), null);
//        canvas.drawBitmap(bitmap2, new Matrix(), null);
        return overlayBitmap;

    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {

    }
}
