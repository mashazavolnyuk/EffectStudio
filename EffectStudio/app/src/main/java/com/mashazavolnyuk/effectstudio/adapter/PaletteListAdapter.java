package com.mashazavolnyuk.effectstudio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mashazavolnyuk.effectstudio.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dark Maleficent on 09.10.2016.
 */

public class PaletteListAdapter extends RecyclerView.Adapter<PaletteListHolders> {
    Context context;
    //    String hexColor = String.format("#%06X", (0xFFFFFF & intColor));
    private List<Integer> color = new ArrayList<>();

    public PaletteListAdapter(Context context, List<Integer> color) {
        this.context = context;
        this.color=color;
    }

    @Override
    public PaletteListHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.item_list_effect_tools, null);
        PaletteListHolders rcv = new PaletteListHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(PaletteListHolders holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }
}
