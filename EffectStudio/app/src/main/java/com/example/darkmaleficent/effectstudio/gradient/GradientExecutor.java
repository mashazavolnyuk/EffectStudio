package com.example.darkmaleficent.effectstudio.gradient;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.darkmaleficent.effectstudio.interfaces.IExecutor;
import com.example.darkmaleficent.effectstudio.interfaces.ISimpleChangeImage;

/**
 * Created by Dark Maleficent on 07.09.2016.
 */
public class GradientExecutor implements IExecutor {

    private Bitmap bmp;
    private static GradientExecutor instance;


    public static GradientExecutor getInstance() {
        if (instance == null)
            synchronized (GradientExecutor.class) {
                if (instance == null)
                    instance = new GradientExecutor();
            }
        return instance;
    }

    @Override
    public Bitmap execute(int id, Bitmap bitmap, Context context) {
        ISimpleChangeImage effect = null;
        switch (id) {
            case Gradient.PacificOcean:
                effect = new GradientPacificOcean();
                break;
        }
        if (effect != null)
            bmp = effect.apply(bitmap);
        return bmp;
    }
    }

