package com.example.darkmaleficent.effectstudio.effect;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.darkmaleficent.effectstudio.R;
import com.mukesh.image_processing.ImageProcessor;

/**
 * Created by Dark Maleficent on 28.07.2016.
 */
public class ShowEffect extends SingleImageEffect {

    public ShowEffect() {
        super(7, "ShowEffect", R.mipmap.ic_add_a_photo_white_36dp);
    }

    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
        ImageProcessor imageProcessor = new ImageProcessor();
        Bitmap outputImage = imageProcessor.applySnowEffect(bitmap);
        return outputImage;
    }
}
