package com.mashazavolnyuk.effectstudio.effect;

import android.graphics.Bitmap;

import com.mukesh.image_processing.ImageProcessor;

/**
 * Created by Dark Maleficent on 28.07.2016.
 */
public class ShowEffect extends Effect {

    public ShowEffect() {
        super(7, "ShowEffect");
    }

    @Override
    public Bitmap apply(Bitmap bitmap) {
        ImageProcessor imageProcessor = new ImageProcessor();
        Bitmap outputImage = imageProcessor.applySnowEffect(bitmap);
        return outputImage;
    }
}
