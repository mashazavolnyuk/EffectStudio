package com.example.darkmaleficent.effectstudio;

import android.graphics.Bitmap;

/**
 * Created by Dark Maleficent on 31.05.2016.
 */
public interface INavigation {
    void toGridView();
    void toModifyImage(Bitmap image);
    void loadImagefromGallery();
    void loadImagefromCamera();
}
