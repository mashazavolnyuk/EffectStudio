package com.mashazavolnyuk.effectstudio.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mashazavolnyuk.effectstudio.ImageChanger;
import com.mashazavolnyuk.effectstudio.MainActivity;
import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.interfaces.ISimpleChangeImage;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public abstract class Filter extends ImageChanger implements ISimpleChangeImage {

    Context context= MainActivity.getContext();
    private  Bitmap originalPreview =BitmapFactory.decodeResource(context.getResources(), R.mipmap.preview);
    Bitmap mutableBitmap=originalPreview.copy(Bitmap.Config.ARGB_8888, true);
    private Bitmap preview;

    static final int AquaFilter = 1;
    static final int DescreaseColor = 2;
    static final int HotSun = 3;
    static final int Grass = 4;
    static final int SAND = 5;
    static final int RoseWater=6;
    static final int Mysticism=7;
    static final int Gray=8;

    public abstract int getId();

    public Filter(String name) {
        super( name);
    }
    public Bitmap getPreview() {
        return preview=apply(mutableBitmap);
    }
}