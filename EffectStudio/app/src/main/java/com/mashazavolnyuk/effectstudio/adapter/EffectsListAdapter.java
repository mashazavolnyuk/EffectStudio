package com.mashazavolnyuk.effectstudio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.data.ChangeImageStorage;
import com.mashazavolnyuk.effectstudio.effect.Effect;

import java.util.List;

public class EffectsListAdapter extends RecyclerView.Adapter<EffectsListHolders> {

    private Context context;
    private List<Effect> data;

    public EffectsListAdapter(Context context) {
        this.context = context;
        data = ChangeImageStorage.getInstance().getEffects();
    }
    @Override
    public EffectsListHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.item_list_effect_tools, null);
        EffectsListHolders rcv = new EffectsListHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(EffectsListHolders holder, final int position) {
        holder.description.setText(data.get(position).getName());
        holder.effectName = data.get(position).getClass().getName();
        holder.effect.setImageBitmap(data.get(position).getPreview());
        holder.position = position;
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
}
