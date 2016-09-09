package com.mashazavolnyuk.effectstudio.data;

import android.graphics.Bitmap;

import com.mashazavolnyuk.effectstudio.interfaces.IObservableWorkingImage;
import com.mashazavolnyuk.effectstudio.interfaces.IObserveWorkingImage;

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
        observeWorkingImage.newState(true);
    }

    public Bitmap getBmp() {
        return bmp;
    }

    @Override
    public void setObserver(IObserveWorkingImage observer) {
        observeWorkingImage = observer;
    }
}
