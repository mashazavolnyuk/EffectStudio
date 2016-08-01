package com.example.darkmaleficent.effectstudio.data;

import android.graphics.Bitmap;

import com.example.darkmaleficent.effectstudio.interfaces.IObservableWorkingImage;
import com.example.darkmaleficent.effectstudio.interfaces.IObserveWorkingImage;

import java.io.Serializable;

/**
 * Created by Dark Maleficent on 09.06.2016.
 */
public class ImageStorage implements IObservableWorkingImage, Serializable {
    private static ImageStorage intance;
    private Bitmap bmp;
    private IObserveWorkingImage observeWorkingImage;

    private ImageStorage() {
    }

    public static ImageStorage getInstance() {
        if (intance == null)
            synchronized (ImageStorage.class) {
                if (intance == null)
                    intance = new ImageStorage();
            }
        return intance;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public Bitmap getBmp() {
        return bmp;
    }

    private void ReplaceImage(int index, Bitmap newObject) {
        setBmp(newObject);
        observeWorkingImage.newState(true);
    }


    @Override
    public void setObserver(IObserveWorkingImage observer) {
        observeWorkingImage = observer;
    }
}
