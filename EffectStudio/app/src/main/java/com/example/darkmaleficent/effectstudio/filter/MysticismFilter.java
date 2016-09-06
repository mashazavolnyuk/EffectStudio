package com.example.darkmaleficent.effectstudio.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;

import com.example.darkmaleficent.effectstudio.MainActivity;
import com.example.darkmaleficent.effectstudio.R;

/**
 * Created by Dark Maleficent on 01.09.2016.
 */
public class MysticismFilter extends TintFilter {
    private int id;
    Context context;



    public MysticismFilter() {
        super(Filter.Mysticism, "Mysticism");
        id=Filter.Mysticism;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Bitmap apply( Bitmap bitmap) {
        this.context = context;
        int color1 = ContextCompat.getColor(MainActivity.getContext(), R.color.Mysticism);
        Bitmap bmp = addTint(bitmap, color1,0);
        return bmp;
    }
}
