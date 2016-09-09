package com.mashazavolnyuk.effectstudio.effect;

import android.content.Context;
import android.graphics.Bitmap;

import com.mashazavolnyuk.effectstudio.interfaces.IExecutor;
import com.mashazavolnyuk.effectstudio.interfaces.ISimpleChangeImage;

/**
 * Created by Dark Maleficent on 20.06.2016.
 */
public class EffectExecutor implements IExecutor {

    private Bitmap bmp;
    private static EffectExecutor instance;

    private EffectExecutor() {
    }

    public static EffectExecutor getInstance() {
        if (instance == null)
            synchronized (EffectExecutor.class) {
                if (instance == null)
                    instance = new EffectExecutor();

            }
        return instance;
    }
    @Override
    public Bitmap execute(int id, Bitmap bitmap, Context context) {

        ISimpleChangeImage effect = null;
        switch (id) {
            case 1:
                effect = new GrayScaleEffect();
                break;
            case 2:
                effect = new BlurEffect();
                break;
            case 3:
                effect = new BlurPixelEffect();
                break;
            case 4:
                effect = new ReflectionEffect();
                break;
            case 5:
                effect = new RemovalEffect();
                break;
            case 6:
                effect = new FleaEffect();
                break;
            case 7:
                effect = new ShowEffect();
                break;
            case 8:
                effect = new TestEffect();
                break;
        }
        if(effect != null)
            bmp = effect.apply(bitmap);
        return bmp;
    }

}
