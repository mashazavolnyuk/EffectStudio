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
public class GrassFilter extends Filter implements ISingleImageEffect {
    int id;

    public GrassFilter() {
        super(Filter.Grass, "Grass", R.mipmap.ic_mode_edit_white_48dp);
        id=Filter.Grass;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
        ImageProcessor processor=new ImageProcessor();
        Bitmap b=processor.boost(bitmap, ImageProcessingConstants.GREEN,0.6);
        return b;

    }
}
