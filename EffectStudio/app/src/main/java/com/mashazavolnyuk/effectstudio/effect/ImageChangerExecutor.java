package com.mashazavolnyuk.effectstudio.effect;

import android.content.Context;
import android.graphics.Bitmap;

import com.mashazavolnyuk.effectstudio.ImageChanger;
import com.mashazavolnyuk.effectstudio.interfaces.IExecutor;
import com.mashazavolnyuk.effectstudio.interfaces.ISimpleChangeImage;

/**
 * Created by Dark Maleficent on 20.06.2016.
 */
public class ImageChangerExecutor implements IExecutor {

    private Bitmap bmp;
    private static ImageChangerExecutor instance;

    private ImageChangerExecutor() {
    }

    public static ImageChangerExecutor getInstance() {
        if (instance == null)
            synchronized (ImageChangerExecutor.class) {
                if (instance == null)
                    instance = new ImageChangerExecutor();

            }
        return instance;
    }
    @Override
    public Bitmap execute(String effectName, Bitmap bitmap, Context context) {
        ImageChanger effect = ImageChangerFactory.createChanger(effectName);
        if(effect != null && effect instanceof ISimpleChangeImage)
            bmp = ((ISimpleChangeImage)effect).apply(bitmap);
        return bmp;
    }

}
