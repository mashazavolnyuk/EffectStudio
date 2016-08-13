package com.example.darkmaleficent.effectstudio.filter;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.effect.ISingleImageEffect;
import com.mukesh.image_processing.ImageProcessingConstants;
import com.mukesh.image_processing.ImageProcessor;

/**
 * Created by Dark Maleficent on 13.08.2016.
 */
public class PacificOceanFilter extends Filter implements ISingleImageEffect {

    int id;
    public PacificOceanFilter() {
        super(Filter.PacificOcean, "PacificOcean", R.mipmap.ic_mode_edit_white_48dp);
        id=Filter.PacificOcean;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
        ImageProcessor processor=new ImageProcessor();
        Bitmap bmp=processor.boost(bitmap, ImageProcessingConstants.BLUE,1);
        Bitmap b=processor.doGamma(bmp,1,2.5,4.5);
        return b;
    }
}
