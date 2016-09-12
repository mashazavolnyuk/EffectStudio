package com.mashazavolnyuk.effectstudio.gradient;

import android.content.Context;
import android.graphics.Bitmap;

import com.mashazavolnyuk.effectstudio.interfaces.IExecutor;
import com.mashazavolnyuk.effectstudio.interfaces.ISimpleChangeImage;

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
            case  Gradient.Fuchsia:
                effect=new GradientFuchsia();
                break;
            case Gradient.MintTea:
                effect=new GradientMintTea();
                break;
            case Gradient.BarbieDoll:
                effect=new GradientBarbieDoll();
                break;
            case Gradient.GreenDay:
                effect=new GradientGreenDay();
                break;
            case Gradient.MiamiBeach:
                effect=new GradientMiamiBeach();
                break;
            case Gradient.CherryBlossoms:
                effect=new GradientCherryBlossoms();
                break;
        }
        if (effect != null)
            bmp = effect.apply(bitmap);
        return bmp;
    }
    }

