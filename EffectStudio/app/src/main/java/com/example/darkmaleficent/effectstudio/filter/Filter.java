package com.example.darkmaleficent.effectstudio.filter;

import com.example.darkmaleficent.effectstudio.ChangeImage;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public abstract class Filter extends ChangeImage {
    public Filter(int id, String name, int image) {
        super(id, name, image);
    }
}
