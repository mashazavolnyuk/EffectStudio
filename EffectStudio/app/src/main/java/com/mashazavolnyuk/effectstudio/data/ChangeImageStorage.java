package com.mashazavolnyuk.effectstudio.data;

import com.mashazavolnyuk.effectstudio.effect.BlurPixelEffect;
import com.mashazavolnyuk.effectstudio.effect.Effect;
import com.mashazavolnyuk.effectstudio.effect.FleaEffect;
import com.mashazavolnyuk.effectstudio.effect.GrayScaleEffect;
import com.mashazavolnyuk.effectstudio.effect.ReflectionEffect;
import com.mashazavolnyuk.effectstudio.effect.RemovalEffect;
import com.mashazavolnyuk.effectstudio.effect.ShowEffect;
import com.mashazavolnyuk.effectstudio.effect.TestEffect;
import com.mashazavolnyuk.effectstudio.filter.AquaFilter;
import com.mashazavolnyuk.effectstudio.filter.DecreaseFilter;
import com.mashazavolnyuk.effectstudio.filter.Filter;
import com.mashazavolnyuk.effectstudio.filter.GrassFilter;
import com.mashazavolnyuk.effectstudio.filter.GrayFilter;
import com.mashazavolnyuk.effectstudio.filter.HotSunFilter;
import com.mashazavolnyuk.effectstudio.filter.MysticismFilter;
import com.mashazavolnyuk.effectstudio.filter.RoseWaterFilter;
import com.mashazavolnyuk.effectstudio.filter.Sand;
import com.mashazavolnyuk.effectstudio.gradient.Gradient;
import com.mashazavolnyuk.effectstudio.gradient.GradientBarbieDoll;
import com.mashazavolnyuk.effectstudio.gradient.GradientCherryBlossoms;
import com.mashazavolnyuk.effectstudio.gradient.GradientFuchsia;
import com.mashazavolnyuk.effectstudio.gradient.GradientGreenDay;
import com.mashazavolnyuk.effectstudio.gradient.GradientMiamiBeach;
import com.mashazavolnyuk.effectstudio.gradient.GradientMintTea;
import com.mashazavolnyuk.effectstudio.gradient.GradientPacificOcean;
import com.mashazavolnyuk.effectstudio.property.BrightnessProperty;
import com.mashazavolnyuk.effectstudio.property.ContrastProperty;
import com.mashazavolnyuk.effectstudio.property.OpacityProperty;
import com.mashazavolnyuk.effectstudio.property.Property;

import java.util.ArrayList;
import java.util.List;


public class ChangeImageStorage {

    private static ChangeImageStorage instance;
    private List<Effect> effects = new ArrayList<>();
    private List<Gradient> gradients=new ArrayList<>();
    private List<Property> properties = new ArrayList<>();
    private List<com.mashazavolnyuk.effectstudio.filter.Filter> filters = new ArrayList<>();

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
        fillEffects();
        return effects;
    }

    public List<Property> getProperties() {
        fillProperties();
        return properties;

    }

    public List<Gradient> getGradients() {
        fillGradients();
        return gradients;

    }
    private void fillGradients(){
        gradients.clear();
        gradients.add(new GradientPacificOcean());
        gradients.add(new GradientFuchsia());
        gradients.add(new GradientMintTea());
        gradients.add(new GradientBarbieDoll());
        gradients.add(new GradientGreenDay());
        gradients.add(new GradientMiamiBeach());
        gradients.add(new GradientCherryBlossoms());
    }
    private void fillProperties() {
        properties.clear();
        properties.add(new BrightnessProperty());
        properties.add(new ContrastProperty());
        properties.add(new OpacityProperty());

    }

    private void fillEffects() {
        effects.clear();
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
