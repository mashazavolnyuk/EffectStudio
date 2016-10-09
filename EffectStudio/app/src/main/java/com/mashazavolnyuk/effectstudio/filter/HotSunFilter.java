package com.mashazavolnyuk.effectstudio.filter;

import android.graphics.Bitmap;

import com.mukesh.image_processing.ImageProcessor;

/**
 * Created by Dark Maleficent on 13.08.2016.
 */
public class HotSunFilter extends Filter {
    int id;

    public HotSunFilter() {
        super("Hot Sun");

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Bitmap apply( Bitmap bitmap) {
        ImageProcessor processor = new ImageProcessor();
//        Bitmap bmp=processor.boost(bitmap, ImageProcessingConstants.RED,0.3);
//        Bitmap bmp=processor.boost(bitmap, ImageProcessingConstants.RED,0.3);
        //Recept 3 "Sunset"
        Bitmap bmp = processor.doGamma(bitmap, 3.5, 1, 1);
//        return r;
        return bmp;
    }
}
