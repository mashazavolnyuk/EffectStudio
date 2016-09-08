package com.example.darkmaleficent.effectstudio.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;

import com.example.darkmaleficent.effectstudio.MainActivity;
import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.interfaces.ISimpleChangeImage;

/**
 * Created by Dark Maleficent on 13.08.2016.
 */
public class Sand extends TintFilter implements ISimpleChangeImage {

    int id;
    Context context;

    public Sand() {
        super(Filter.SAND, "Sand");
        id = Filter.SAND;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Bitmap apply( Bitmap bitmap) {

        int color1 = ContextCompat.getColor(MainActivity.getContext(), R.color.Sand);
        Bitmap bmp = addTint(bitmap, color1,0);
       // Bitmap bmp=addGradient(bitmap, Color.BLUE,Color.GREEN);
        return bmp;
    }



}
