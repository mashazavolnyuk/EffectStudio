package com.example.darkmaleficent.effectstudio.effect;

import java.util.ArrayList;
import java.util.List;


public class EffectStorage {

    private static EffectStorage instance;
    private List<Effect> effects = new ArrayList<>();

    private EffectStorage() {
    }

    public static EffectStorage getInstance() {
        if (instance == null)
            synchronized (EffectStorage.class) {
                if (instance == null)
                    instance = new EffectStorage();

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
        effects.add(new TestEffect());
    }
}
