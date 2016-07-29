package com.example.darkmaleficent.effectstudio.effect;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.darkmaleficent.effectstudio.R;
import com.mukesh.image_processing.ImageProcessor;

/**
 * Created by Dark Maleficent on 28.07.2016.
 */
public class FleaEffect extends SingleImageEffect {

    public FleaEffect() {
        super(6, "FleaEffect", R.mipmap.ic_add_a_photo_white_36dp);
    }

    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
        ImageProcessor imageProcessor = new ImageProcessor();
        Bitmap outputImage = imageProcessor.applyFleaEffect(bitmap);
        return outputImage;
    }
}
