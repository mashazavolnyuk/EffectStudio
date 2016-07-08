package com.example.darkmaleficent.effectstudio;

import android.content.Context;
import android.graphics.Bitmap;

import com.mukesh.image_processing.ImageProcessor;

/**
 * Created by Dark Maleficent on 29.06.2016.
 */
public class SnowEffect extends SingleImageEffect {

    public SnowEffect() {
        super(5, "Snow", R.mipmap.ic_add_a_photo_white_36dp,TypeEffect.Filter);
    }

    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
        ImageProcessor imageProcessor = new ImageProcessor();
        Bitmap outputImage = imageProcessor.doBrightness(bitmap,240);
        return outputImage;
    }
}
