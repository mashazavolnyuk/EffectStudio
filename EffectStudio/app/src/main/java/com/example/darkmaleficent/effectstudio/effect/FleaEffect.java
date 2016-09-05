package com.example.darkmaleficent.effectstudio.effect;

import android.graphics.Bitmap;

import com.mukesh.image_processing.ImageProcessor;

/**
 * Created by Dark Maleficent on 28.07.2016.
 */
public class FleaEffect extends Effect {
    public FleaEffect() {
        super(6, "FleaEffect");

    }

    @Override
    public Bitmap apply(Bitmap bitmap) {
        ImageProcessor imageProcessor = new ImageProcessor();
        Bitmap outputImage = imageProcessor.applyFleaEffect(bitmap);
        return outputImage;
    }


}
