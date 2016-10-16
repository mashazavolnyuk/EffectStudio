package com.aquarium.mashazavolnyuk.testfirebase.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aquarium.mashazavolnyuk.testfirebase.R;

/**
 * Created by Dark Maleficent on 28.09.2016.
 */
public class RecyclerViewHolders extends android.support.v7.widget.RecyclerView.ViewHolder {
    int id;
    ImageView imgCategory;
    TextView nameCategory;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        nameCategory = (TextView) itemView.findViewById(R.id.tvNameCategory);
        imgCategory = (ImageView) itemView.findViewById(R.id.imgCategory);
    }
}
