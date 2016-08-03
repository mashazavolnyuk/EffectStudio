package com.example.darkmaleficent.effectstudio.property;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.darkmaleficent.effectstudio.interfaces.IExecutor;
import com.example.darkmaleficent.effectstudio.interfaces.IRegulator;

/**
 * Created by Dark Maleficent on 01.08.2016.
 */
public class PropertyExecutor implements IExecutor {

    private Bitmap bmp;
    private static PropertyExecutor instance;

    private PropertyExecutor() {
    }

    public static PropertyExecutor getInstance() {
        if (instance == null)
            synchronized (PropertyExecutor.class) {
                if (instance == null)
                    instance = new PropertyExecutor();

            }
        return instance;
    }


    @Override
    public Bitmap execute(int id, Bitmap bitmap, Context context) {

        IRegulator regulator = null;

        switch (id) {
            case Property.Brightness:
                regulator = new BrightnessProperty();
                regulator.setMaxMin(255,0);
                break;
            case Property.Contrast:
                regulator = new ContrastProperty();
                regulator.setMaxMin(255,0);
                break;
            case Property.Opacity:
                break;


    }
        bmp = regulator.apply(context,bitmap);
        return bmp;
}

}