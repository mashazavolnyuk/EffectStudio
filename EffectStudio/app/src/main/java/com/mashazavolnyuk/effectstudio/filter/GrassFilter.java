package com.mashazavolnyuk.effectstudio.filter;

import android.graphics.Bitmap;

import com.mukesh.image_processing.ImageProcessingConstants;
import com.mukesh.image_processing.ImageProcessor;

/**
 * Created by Dark Maleficent on 13.08.2016.
 */
public class GrassFilter extends Filter  {


    public GrassFilter() {
        super( "Grass");

    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public Bitmap apply(Bitmap bitmap) {
        ImageProcessor processor = new ImageProcessor();
        Bitmap b = processor.boost(bitmap, ImageProcessingConstants.GREEN, 0.6);
        return b;

    }
}
