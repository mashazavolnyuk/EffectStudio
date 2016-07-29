package com.example.darkmaleficent.effectstudio.effect;

/**
 * Created by Dark Maleficent on 17.06.2016.
 */
public abstract class DoubleImageEffect extends Effect implements IDoubleImageEffect {

    public DoubleImageEffect() {
    }

    public DoubleImageEffect(int id, String name, int image, TypeEffect typeEffect) {
        super(id, name, image);
    }
}
