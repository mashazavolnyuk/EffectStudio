package com.mashazavolnyuk.effectstudio.filter;

import android.graphics.Bitmap;

import com.mukesh.image_processing.ImageProcessingConstants;
import com.mukesh.image_processing.ImageProcessor;

/**
 * Created by Dark Maleficent on 12.08.2016.
 */
public class AquaFilter extends Filter {
    int id;

    public AquaFilter() {
        super(Filter.AquaFilter, "Aqua");
        id = Filter.AquaFilter;
    }

    @Override
    public Bitmap apply(Bitmap bitmap) {
        ImageProcessor processor = new ImageProcessor();
        // Bitmap bmp=processor.applyShadingFilter(bitmap, Color.RED);
        //  Bitmap bmp=processor.decreaseColorDepth(bitmap,120); //+
        // Bitmap bmp=processor.emboss(bitmap);//добавить в эффекты "тиснение"
        Bitmap bmp = processor.boost(bitmap, ImageProcessingConstants.GREEN, 0.2);
        Bitmap q = processor.boost(bmp, ImageProcessingConstants.BLUE, 0.3);
        return q;
    }

    @Override
    public int getId() {
        return id;
    }
}
