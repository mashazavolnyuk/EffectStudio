package com.example.darkmaleficent.effectstudio.interfaces;

import com.example.darkmaleficent.effectstudio.effect.ISingleImageEffect;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public interface IRegulator extends ISingleImageEffect {
    void setMaxMin(int Max,int Min);
}
