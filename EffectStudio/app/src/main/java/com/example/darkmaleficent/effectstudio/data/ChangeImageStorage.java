package com.example.darkmaleficent.effectstudio.data;

import com.example.darkmaleficent.effectstudio.effect.BlurEffect;
import com.example.darkmaleficent.effectstudio.effect.BlurPixelEffect;
import com.example.darkmaleficent.effectstudio.effect.Effect;
import com.example.darkmaleficent.effectstudio.effect.FleaEffect;
import com.example.darkmaleficent.effectstudio.effect.GrayScaleEffect;
import com.example.darkmaleficent.effectstudio.effect.ReflectionEffect;
import com.example.darkmaleficent.effectstudio.effect.RemovalEffect;
import com.example.darkmaleficent.effectstudio.effect.ShowEffect;
import com.example.darkmaleficent.effectstudio.property.BrightnessProperty;
import com.example.darkmaleficent.effectstudio.property.ContrastProperty;
import com.example.darkmaleficent.effectstudio.property.Property;

import java.util.ArrayList;
import java.util.List;


public class ChangeImageStorage {

    private static ChangeImageStorage instance;
    private List<Effect> effects=new ArrayList<>();
    private List<Property> properties=new ArrayList<>();

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
        fiilEffects();
        return effects;
    }
    public List<Property> getProperties(){
        fillProperties();
        return properties;

    }
    private void fillProperties(){
        properties.clear();
        properties.add(new BrightnessProperty());
        properties.add(new ContrastProperty());

    }
    private void fiilEffects() {
        effects.clear();
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
