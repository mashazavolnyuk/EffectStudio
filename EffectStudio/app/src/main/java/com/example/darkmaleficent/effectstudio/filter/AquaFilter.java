package com.example.darkmaleficent.effectstudio.filter;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.effect.ISingleImageEffect;
import com.mukesh.image_processing.ImageProcessingConstants;
import com.mukesh.image_processing.ImageProcessor;

/**
 * Created by Dark Maleficent on 12.08.2016.
 */
public class AquaFilter extends Filter implements ISingleImageEffect {
    int id;
    public AquaFilter() {
        super(Filter.AquaFilter, "Aqua", R.mipmap.ic_mode_edit_white_48dp);
        id=Filter.AquaFilter;
    }

    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
        ImageProcessor processor=new ImageProcessor();
       // Bitmap bmp=processor.applyShadingFilter(bitmap, Color.RED);
       //  Bitmap bmp=processor.decreaseColorDepth(bitmap,120); //+
       // Bitmap bmp=processor.emboss(bitmap);//добавить в эффекты "тиснение"
        Bitmap bmp=processor.boost(bitmap, ImageProcessingConstants.GREEN,0.2);
        Bitmap q=processor.boost(bmp, ImageProcessingConstants.BLUE,0.2);
        return q;
    }

    @Override
    public int getId() {
        return id;
    }
}
