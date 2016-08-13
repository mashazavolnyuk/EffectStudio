package com.example.darkmaleficent.effectstudio.filter;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.effect.ISingleImageEffect;
import com.mukesh.image_processing.ImageProcessor;

/**
 * Created by Dark Maleficent on 13.08.2016.
 */
public class HotSunFilter extends Filter implements ISingleImageEffect {
    int id;

    public HotSunFilter() {
        super(Filter.HotSun, "Hot Sun", R.color.Aqua);
        id=Filter.HotSun;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
        ImageProcessor processor=new ImageProcessor();
//        Bitmap bmp=processor.boost(bitmap, ImageProcessingConstants.RED,0.3);
//        Bitmap bmp=processor.boost(bitmap, ImageProcessingConstants.RED,0.3);
        //Recept 3 "Sunset"
        Bitmap bmp=processor.doGamma(bitmap,3.5,1,1);
//        return r;
        return bmp;
    }
}
