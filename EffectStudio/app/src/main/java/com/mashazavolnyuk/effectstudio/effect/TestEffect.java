package com.mashazavolnyuk.effectstudio.effect;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

import com.mashazavolnyuk.effectstudio.R;


/**
 * Created by Dark Maleficent on 28.07.2016.
 */
public class TestEffect extends Effect {



     public TestEffect() {
        super(8, "Test");
    }


    @Override
    public Bitmap apply( Bitmap bitmap) {
        Bitmap bitmap2= BitmapFactory.decodeResource(Resources.getSystem(),R.mipmap.smurfs);
        Bitmap overlayBitmap = Bitmap.createBitmap(bitmap);
        Canvas canvas = new Canvas(overlayBitmap);
        canvas.setBitmap(overlayBitmap);
        canvas.drawBitmap(bitmap,new Matrix(),null);
//        canvas.drawBitmap(bitmap2, new Matrix(), null);
        return overlayBitmap;

    }
}
