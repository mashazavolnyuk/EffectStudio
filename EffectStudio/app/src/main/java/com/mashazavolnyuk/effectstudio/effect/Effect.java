package com.mashazavolnyuk.effectstudio.effect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mashazavolnyuk.effectstudio.ImageChanger;
import com.mashazavolnyuk.effectstudio.MainActivity;
import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.interfaces.ISimpleChangeImage;

/**
 * Created by Dark Maleficent on 17.06.2016.
 */
public abstract class Effect extends ImageChanger implements ISimpleChangeImage {
    Context context = MainActivity.getContext();
    private Bitmap originalPreview = BitmapFactory.decodeResource(context.getResources(), R.mipmap.preview);
    Bitmap mutableBitmap = originalPreview.copy(Bitmap.Config.ARGB_8888, true);
    private Bitmap preview;

    public Effect(String name) {
        super(name);

    }

    public Bitmap getPreview() {
        return preview = apply(mutableBitmap);
    }

}
