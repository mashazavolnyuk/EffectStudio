package com.mashazavolnyuk.effectstudio.interfaces;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by Dark Maleficent on 01.08.2016.
 */
public interface IExecutor {
    Bitmap execute(String effectName, Bitmap bitmap, Context context);
}