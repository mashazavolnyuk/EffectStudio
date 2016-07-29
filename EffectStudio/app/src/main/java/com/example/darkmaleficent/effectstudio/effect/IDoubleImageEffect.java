package com.example.darkmaleficent.effectstudio.effect;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by Dark Maleficent on 17.06.2016.
 */
public interface IDoubleImageEffect {
    Bitmap process(Context context, Bitmap bitmap1, Bitmap bitmap2);
}
