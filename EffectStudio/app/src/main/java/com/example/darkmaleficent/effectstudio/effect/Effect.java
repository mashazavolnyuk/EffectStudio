package com.example.darkmaleficent.effectstudio.effect;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.darkmaleficent.effectstudio.ChangeImage;
import com.example.darkmaleficent.effectstudio.MainActivity;
import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.interfaces.ISimpleChangeImage;

/**
 * Created by Dark Maleficent on 17.06.2016.
 */
public abstract class Effect extends ChangeImage implements ISimpleChangeImage {
    Context context= MainActivity.getContext();
    private  Bitmap originalPreview =BitmapFactory.decodeResource(context.getResources(), R.mipmap.preview);
    Bitmap mutableBitmap=originalPreview.copy(Bitmap.Config.ARGB_8888, true);

    private Bitmap preview;
    public Effect(int id, String name) {
        super(id, name);

    }
    public Bitmap getPreview() {
        return preview=apply(mutableBitmap);
    }

}
