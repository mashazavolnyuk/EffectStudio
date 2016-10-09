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
    private List<Integer> colors = new ArrayList<>();
    String hexColor;

    public PaletteListAdapter(Context context, List<Integer> colors) {
        this.colors = colors;
        this.context = context;
    }


    @Override
    public PaletteListHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.item_palette_colors, null);
        PaletteListHolders rcv = new PaletteListHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(PaletteListHolders holder, int position) {
        int color=colors.get(position);
        holder.name = hexColor = String.format("#%06X", (0xFFFFFF & color));
        holder.color = colors.get(position);
        holder.imageView.setBackgroundColor(color);
        holder.textView.setText(hexColor);

    }


    @Override
    public int getItemCount() {
        return colors.size();
    }
}
