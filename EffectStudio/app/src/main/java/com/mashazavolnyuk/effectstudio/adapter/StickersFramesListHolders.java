package com.mashazavolnyuk.effectstudio.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dark Maleficent on 19.10.2016.
 */

public class StickersFramesListHolders extends RecyclerView.ViewHolder {
    TextView textCover;
    ImageView imgCover;
    List<String> urlStickers;
    public StickersFramesListHolders(View itemView) {
        super(itemView);
    }
}
