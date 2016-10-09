package com.mashazavolnyuk.effectstudio.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mashazavolnyuk.effectstudio.R;

/**
 * Created by Dark Maleficent on 09.10.2016.
 */

public class PaletteListHolders extends RecyclerView.ViewHolder {
    String name;
    ImageView imageView;
    TextView textView;
    int color;

    public PaletteListHolders(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imgPalette);
        imageView.setBackgroundColor(color);
        textView = (TextView) itemView.findViewById(R.id.tvColorRGB);
        textView.setText(name);
    }
}
