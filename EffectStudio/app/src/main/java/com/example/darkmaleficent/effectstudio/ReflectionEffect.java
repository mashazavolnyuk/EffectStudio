package com.example.darkmaleficent.effectstudio;

import android.content.Context;
import android.graphics.Bitmap;

import com.mukesh.image_processing.ImageProcessor;

/**
 * Created by Dark Maleficent on 29.06.2016.
 */
public class ReflectionEffect extends SingleImageEffect {

    public ReflectionEffect() {
        super(4, " Reflection", R.mipmap.ic_loop_white_36dp,TypeEffect.Filter);
    }

    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
//        Filter fooFilter = SampleFilters.getBlueMessFilter();
//        Bitmap outputImage = fooFilter.processFilter(bitmap);
        ImageProcessor imageProcessor = new ImageProcessor();
        Bitmap outputImage = imageProcessor.applyReflection(bitmap);
        return outputImage;

    }
}
