package com.example.darkmaleficent.effectstudio.data;

import com.example.darkmaleficent.effectstudio.effect.BlurEffect;
import com.example.darkmaleficent.effectstudio.effect.BlurPixelEffect;
import com.example.darkmaleficent.effectstudio.effect.Effect;
import com.example.darkmaleficent.effectstudio.effect.FleaEffect;
import com.example.darkmaleficent.effectstudio.effect.GrayScaleEffect;
import com.example.darkmaleficent.effectstudio.effect.ReflectionEffect;
import com.example.darkmaleficent.effectstudio.effect.RemovalEffect;
import com.example.darkmaleficent.effectstudio.effect.ShowEffect;
import com.example.darkmaleficent.effectstudio.effect.TestEffect;
import com.example.darkmaleficent.effectstudio.filter.AquaFilter;
import com.example.darkmaleficent.effectstudio.filter.DecreaseFilter;
import com.example.darkmaleficent.effectstudio.filter.Filter;
import com.example.darkmaleficent.effectstudio.filter.GrassFilter;
import com.example.darkmaleficent.effectstudio.filter.GrayFilter;
import com.example.darkmaleficent.effectstudio.filter.HotSunFilter;
import com.example.darkmaleficent.effectstudio.filter.MysticismFilter;
import com.example.darkmaleficent.effectstudio.filter.RoseWaterFilter;
import com.example.darkmaleficent.effectstudio.filter.Sand;
import com.example.darkmaleficent.effectstudio.property.BrightnessProperty;
import com.example.darkmaleficent.effectstudio.property.ContrastProperty;
import com.example.darkmaleficent.effectstudio.property.OpacityProperty;
import com.example.darkmaleficent.effectstudio.property.Property;

import java.util.ArrayList;
import java.util.List;


public class ChangeImageStorage {

    private static ChangeImageStorage instance;
    private List<Effect> effects = new ArrayList<>();
    private List<Property> properties = new ArrayList<>();
    private List<com.example.darkmaleficent.effectstudio.filter.Filter> filters = new ArrayList<>();

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

    public List<Filter> getFilters(){
        fillFilters();
       return filters;

    }
    public List<Effect> getEffects() {
        fiilEffects();
        return effects;
    }

    public List<Property> getProperties() {
        fillProperties();
        return properties;

    }

    private void fillProperties() {
        properties.clear();
        properties.add(new BrightnessProperty());
        properties.add(new ContrastProperty());
        properties.add(new OpacityProperty());

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
        effects.add(new TestEffect());
    }

    private void fillFilters() {
        filters.clear();
        filters.add(new AquaFilter());
        filters.add(new DecreaseFilter());
        filters.add(new HotSunFilter());
        filters.add(new GrassFilter());
        filters.add(new Sand());
        filters.add(new RoseWaterFilter());
        filters.add(new MysticismFilter());
        filters.add(new GrayFilter());


    }
}
