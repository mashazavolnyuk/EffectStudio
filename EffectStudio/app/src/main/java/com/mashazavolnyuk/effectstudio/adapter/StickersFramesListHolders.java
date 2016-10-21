package com.mashazavolnyuk.effectstudio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.interfaces.INavigation;

import java.util.List;

/**
 * Created by Dark Maleficent on 19.10.2016.
 */

public class StickersFramesListHolders extends RecyclerView.ViewHolder {
    TextView textCover;
    ImageView imgCover;
    List<String> urlStickers;
    Context c;


    public StickersFramesListHolders(View itemView) {
        super(itemView);
        textCover= (TextView) itemView.findViewById(R.id.tv_coverName);
        imgCover= (ImageView) itemView.findViewById(R.id.imgCover);
        imgCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ( (INavigation)(c)).toViewOverlayProcess();
            }
        });
    }
}
