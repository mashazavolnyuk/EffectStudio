package com.mashazavolnyuk.effectstudio.effect;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by Dark Maleficent on 17.06.2016.
 */
public class GrayScaleEffect extends Effect {

    public GrayScaleEffect() {
        super(1, "GrayScale");

    }

    @Override
    public Bitmap apply(Bitmap bitmap) {
        Bitmap timebimp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        int red = 0;
        int blue = 0;
        int green = 0;
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                int colour = bitmap.getPixel(i, j);
                red = Color.red(colour);
                blue = Color.blue(colour);
                green = Color.green(colour);
                int newcolor = (red + blue + green) / 3;
                timebimp.setPixel(i, j, Color.rgb(newcolor, newcolor, newcolor));
            }
        }
        return timebimp;
//        Bitmap bitmap2= BitmapFactory.decodeResource(Resources.getSystem(),R.mipmap.smurfs);
//        Bitmap overlayBitmap = Bitmap.createBitmap(bitmap);
//        Canvas canvas = new Canvas(overlayBitmap);
//        canvas.drawBitmap(bitmap,new Matrix(),null);
//        canvas.drawBitmap(bitmap2, new Matrix(), null);
//
//        return overlayBitmap;
    }

}
