package com.example.darkmaleficent.effectstudio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private Context context;
    private List<Effect> data;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        data = EffectStorage.getInstance().getEffects();
        Log.d("Effect size",""+ data.size());
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.item_list_effect_tools, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, final int position) {
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
