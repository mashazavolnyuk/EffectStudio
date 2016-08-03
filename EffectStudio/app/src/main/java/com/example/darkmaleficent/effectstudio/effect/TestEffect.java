package com.example.darkmaleficent.effectstudio.effect;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

import com.example.darkmaleficent.effectstudio.R;


/**
 * Created by Dark Maleficent on 28.07.2016.
 */
public class TestEffect extends SingleImageEffect {



     public TestEffect() {
        super(8, "TestEffect", R.mipmap.ic_add_a_photo_white_36dp);
    }


    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
        Bitmap bitmap2= BitmapFactory.decodeResource(Resources.getSystem(),R.mipmap.smurfs);
        Bitmap overlayBitmap = Bitmap.createBitmap(bitmap);
        Canvas canvas = new Canvas(overlayBitmap);
        canvas.setBitmap(overlayBitmap);
        canvas.drawBitmap(bitmap,new Matrix(),null);
        canvas.drawBitmap(bitmap2, new Matrix(), null);

        return overlayBitmap;

    }
}
