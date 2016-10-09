package com.mashazavolnyuk.effectstudio.data;

import android.graphics.Bitmap;

import com.mashazavolnyuk.effectstudio.interfaces.IObservableWorkingImage;
import com.mashazavolnyuk.effectstudio.interfaces.IObserveWorkingImage;


public class ImageStorage implements IObservableWorkingImage {
    private static ImageStorage intance;
    private Bitmap bmp;
    private Bitmap bmpModify;
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

    public void setBmpModify(Bitmap bmp) {
        this.bmpModify = bmp;

    }

    public Bitmap getBmpModify() {
        return bmpModify;

    }

    public Bitmap getBmpOriginal() {
        return bmp;
    }


    @Override
    public void setObserver(IObserveWorkingImage observer) {
        observeWorkingImage = observer;
    }
}
