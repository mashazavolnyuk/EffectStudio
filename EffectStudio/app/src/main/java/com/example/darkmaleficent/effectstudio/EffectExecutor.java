package com.example.darkmaleficent.effectstudio;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by Dark Maleficent on 20.06.2016.
 */
public class EffectExecutor {
    private List<Effect> data = EffectStorage.getInstance().getEffects();
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

    public Bitmap executeEffect(int id, Bitmap bitmap, Context context) {
        switch (id) {
            case 1:
                GrayScaleEffect grayScaleEffecteffect = new GrayScaleEffect();
                bmp= grayScaleEffecteffect.apply(context, bitmap);
            break;
            case 2:
                BlurEffect blurEffect =new BlurEffect();
                bmp= blurEffect.apply(context, bitmap);
            break;
            case 3:
                BlurPixelEffect blurPixelEffect=new BlurPixelEffect();
                bmp=blurPixelEffect.apply(context, bitmap);
            case 4:
                ReflectionEffect reflectionEffect=new ReflectionEffect();
                bmp=reflectionEffect.apply(context, bitmap);
            case 5:
                SnowEffect snowEffect=new SnowEffect();
                bmp=snowEffect.apply(context,bitmap);
        }
        return bmp;
    }


}
