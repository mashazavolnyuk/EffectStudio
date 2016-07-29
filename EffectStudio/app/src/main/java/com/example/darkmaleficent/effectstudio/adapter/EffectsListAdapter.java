package com.example.darkmaleficent.effectstudio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.effect.Effect;
import com.example.darkmaleficent.effectstudio.effect.EffectStorage;

import java.util.List;

public class EffectsListAdapter extends RecyclerView.Adapter<EffectsListHolders> {

    private Context context;
    private List<Effect> data;

    public EffectsListAdapter(Context context) {
        this.context = context;
        data = EffectStorage.getInstance().getEffects();
        Log.d("Effect size",""+ data.size());
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
        holder.description.setTag(data.get(position).getId());
        holder.effect.setImageResource(data.get(position).getImage());
        holder.position = position;
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
}
