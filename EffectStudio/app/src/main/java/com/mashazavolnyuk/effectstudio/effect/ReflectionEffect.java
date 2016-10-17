package com.mashazavolnyuk.effectstudio.effect;

import android.graphics.Bitmap;

import com.mukesh.image_processing.ImageProcessor;

/**
 * Created by Dark Maleficent on 29.06.2016.
 */
public class ReflectionEffect extends Effect {

    public ReflectionEffect() {
        super("Reflection");

    }

    @Override
    public Bitmap apply(Bitmap bitmap) {
//        Filter fooFilter = SampleFilters.getBlueMessFilter();
//        Bitmap outputImage = fooFilter.processFilter(bitmap);
        ImageProcessor imageProcessor = new ImageProcessor();
        Bitmap outputImage = imageProcessor.applyReflection(bitmap);
        return outputImage;

    }




}
