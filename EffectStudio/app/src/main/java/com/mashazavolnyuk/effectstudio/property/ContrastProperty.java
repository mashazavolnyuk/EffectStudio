package com.mashazavolnyuk.effectstudio.property;

import android.graphics.Bitmap;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.interfaces.ISimpleChangeImage;
import com.mashazavolnyuk.effectstudio.interfaces.IRegulator;
import com.mukesh.image_processing.ImageProcessor;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public class ContrastProperty extends Property implements ISimpleChangeImage, IRegulator {
    int value = 0;
    int id;

    public ContrastProperty() {
        super(Property.Contrast, "Contrast", R.mipmap.ic_mode_edit_white_48dp);
        id = Property.Contrast;
    }

    @Override
    public Bitmap apply(Bitmap bitmap) {
        ImageProcessor processor = new ImageProcessor();
        double doubleValue = value * 0.01;
        return processor.createContrast(bitmap, doubleValue);
    }

    @Override
    public void setMaxMin(int Max, int Min) {
        //todo
        value = Max;
    }

    @Override
    public int getId() {
        return id;
    }
}
