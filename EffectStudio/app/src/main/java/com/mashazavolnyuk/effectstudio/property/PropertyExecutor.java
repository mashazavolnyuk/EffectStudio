package com.mashazavolnyuk.effectstudio.property;

import android.content.Context;
import android.graphics.Bitmap;

import com.mashazavolnyuk.effectstudio.interfaces.IExecutor;
import com.mashazavolnyuk.effectstudio.interfaces.IRegulator;

/**
 * Created by Dark Maleficent on 01.08.2016.
 */
public class PropertyExecutor implements IExecutor {

    private Bitmap bmp;
    private static PropertyExecutor instance;



    public static PropertyExecutor getInstance() {
        if (instance == null)
            synchronized (PropertyExecutor.class) {
                if (instance == null)
                    instance = new PropertyExecutor();

            }
        return instance;
    }



    public Bitmap execute(int id, Bitmap bitmap, Context context) {

        IRegulator regulator = null;

        switch (id) {
            case Property.Brightness:
                regulator = new BrightnessProperty();
                regulator.setMaxMin(240,0);
                break;
            case Property.Contrast:
                regulator = new ContrastProperty();
                regulator.setMaxMin(255,0);
                break;
            case Property.Opacity:
                regulator=new OpacityProperty();
                break;


    }
        bmp = regulator.apply(bitmap);
        return bmp;
}

    @Override
    public Bitmap execute(String effectName, Bitmap bitmap, Context context) {
        return null;
    }
}