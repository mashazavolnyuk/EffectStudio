package com.example.darkmaleficent.effectstudio.filter;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.darkmaleficent.effectstudio.effect.ISingleImageEffect;
import com.example.darkmaleficent.effectstudio.interfaces.IExecutor;

/**
 * Created by Dark Maleficent on 12.08.2016.
 */
public class FilterExecutor implements IExecutor {

    private Bitmap bmp;
    private static FilterExecutor instance;


    public static FilterExecutor getInstance() {
        if (instance == null)
            synchronized (FilterExecutor.class) {
                if (instance == null)
                    instance = new FilterExecutor();

            }
        return instance;
    }


    @Override
    public Bitmap execute(int id, Bitmap bitmap, Context context) {
        ISingleImageEffect effect = null;
        switch (id) {
            case Filter.AquaFilter:
                effect = new AquaFilter();
                break;
            case Filter.DescreaseColor:
                effect = new DecreaseFilter();
                break;
            case Filter.HotSun:
                effect = new HotSunFilter();
                break;
            case Filter.Grass:
                effect = new GrassFilter();
                break;
            case Filter.SAND:
                effect = new Sand();
                break;
            case Filter.RoseWater:
                effect = new RoseWaterFilter();
                break;
            case Filter.Mysticism:
                effect = new MysticismFilter();
                break;
            case Filter.Gray:
                effect=new GrayFilter();
                break;
        }
        if (effect != null)
            bmp = effect.apply( bitmap);
        return bmp;
    }
}
