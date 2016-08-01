package com.example.darkmaleficent.effectstudio.property;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.interfaces.IRegulator;
import com.mukesh.image_processing.ImageProcessor;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public class BrightnessProperty extends Property implements IRegulator {
    int value;
    int id;

    public BrightnessProperty() {
        super(Property.Brightness, "Brightness", R.mipmap.ic_monochrome_photos_white_36dp);
        id=Property.Brightness;
    }

    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
        ImageProcessor processor=new ImageProcessor();
        Bitmap bmp=processor.doBrightness(bitmap,value);
        return bmp;
    }

    @Override
    public void setMaxMin(int Max, int Min) {
        this.value=Max;
    }

    @Override
    public int getId() {
        return id;
    }
}
