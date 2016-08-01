package com.example.darkmaleficent.effectstudio.interfaces;

import android.graphics.Bitmap;

import java.io.IOException;

/**
 * Created by Dark Maleficent on 31.05.2016.
 */
public interface INavigation {
    void toGridView() throws IOException;
    void toModifyImage(Bitmap image);
    void toRegulationProperty(Bitmap image,int idProperties);
    void loadImagefromGallery();
    void loadImagefromCamera();
}
