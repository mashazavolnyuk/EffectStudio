package com.mashazavolnyuk.effectstudio.property;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.interfaces.IRegulator;

/**
 * Created by Dark Maleficent on 03.08.2016.
 */
public class OpacityProperty extends Property implements IRegulator {

    int id=Property.Opacity;
    int value;
    public OpacityProperty() {
        super(Property.Opacity, "Opacity", R.mipmap.ic_mode_edit_white_48dp);
    }

    @Override
    public void setMaxMin(int Max, int Min) {
        this.value=Max;
    }

    @Override
    public Bitmap apply( Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap transBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(transBitmap);
        canvas.drawARGB(0, 0, 0, 0);
        // config paint
        final Paint paint = new Paint();
        paint.setAlpha(value);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return transBitmap;
    }

    @Override
    public int getId() {
        return id;
    }
}
