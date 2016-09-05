package com.example.darkmaleficent.effectstudio.property;

import android.graphics.Bitmap;

import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.effect.ISingleImageEffect;
import com.example.darkmaleficent.effectstudio.interfaces.IRegulator;
import com.mukesh.image_processing.ImageProcessor;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public class ContrastProperty extends Property implements ISingleImageEffect,IRegulator{
    int value=0;
    int id;
    public ContrastProperty() {
        super(Property.Contrast, "Contrast", R.mipmap.ic_mode_edit_white_48dp);
        id=Property.Contrast;
    }

    @Override
    public Bitmap apply( Bitmap bitmap) {
        ImageProcessor processor=new ImageProcessor();
        double doubleValue=value*0.01;
        Bitmap bmp=processor.createContrast(bitmap,doubleValue);
        return bmp;
    }

    @Override
    public void setMaxMin(int Max, int Min) {
       value=Max;
    }

    @Override
    public int getId() {
        return id;
    }
}
