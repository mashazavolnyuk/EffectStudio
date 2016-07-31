package com.example.darkmaleficent.effectstudio.data;

import com.example.darkmaleficent.effectstudio.effect.BlurEffect;
import com.example.darkmaleficent.effectstudio.effect.BlurPixelEffect;
import com.example.darkmaleficent.effectstudio.effect.Effect;
import com.example.darkmaleficent.effectstudio.effect.FleaEffect;
import com.example.darkmaleficent.effectstudio.effect.GrayScaleEffect;
import com.example.darkmaleficent.effectstudio.effect.ReflectionEffect;
import com.example.darkmaleficent.effectstudio.effect.RemovalEffect;
import com.example.darkmaleficent.effectstudio.effect.ShowEffect;

import java.util.ArrayList;
import java.util.List;


public class ChangeImageStorage {

    private static ChangeImageStorage instance;
    private List<Effect> effects = new ArrayList<>();

    private ChangeImageStorage() {
    }

    public static ChangeImageStorage getInstance() {
        if (instance == null)
            synchronized (ChangeImageStorage.class) {
                if (instance == null)
                    instance = new ChangeImageStorage();

            }
        return instance;
    }

    public List<Effect> getEffects() {
        effects.clear();
        fiilEffects();
        return effects;
    }


    private void fiilEffects() {
        effects.add(new BlurEffect());
        effects.add(new GrayScaleEffect());
        effects.add(new BlurPixelEffect());
        effects.add(new ReflectionEffect());
        effects.add(new RemovalEffect());
        effects.add(new FleaEffect());
        effects.add(new ShowEffect());
     //   effects.add(new TestEffect());
    }
}
