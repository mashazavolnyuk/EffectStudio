package com.example.darkmaleficent.effectstudio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.data.ChangeImageStorage;
import com.example.darkmaleficent.effectstudio.property.Property;

import java.util.List;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public class PropertiesAdapter extends  RecyclerView.Adapter<PropertiesHolders> {

    private Context context;
    private List<Property> data;

    public PropertiesAdapter(Context context){

        this.context = context;
        data = ChangeImageStorage.getInstance().getProperties();
        Log.d("Effect size",""+ data.size());
    }
    @Override
    public PropertiesHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.item_list_effect_tools, null);
        PropertiesHolders rcv = new PropertiesHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(PropertiesHolders holder, int position) {
        holder.description.setText(data.get(position).getName());
        holder.description.setTag(data.get(position).getId());
      //  holder.effect.setImageResource(data.get(position).());
        holder.position = position;
        holder.c=context;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
