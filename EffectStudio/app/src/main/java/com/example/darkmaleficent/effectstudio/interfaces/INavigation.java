package com.example.darkmaleficent.effectstudio.interfaces;

import android.graphics.Bitmap;

/**
 * Created by Dark Maleficent on 31.05.2016.
 */
public interface INavigation {
    void toModifyImage();
    void toRegulationProperty(Bitmap image,int idProperties);
    void loadImagefromGallery();
    void loadImagefromCamera();
}
