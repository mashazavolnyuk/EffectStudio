package com.mashazavolnyuk.effectstudio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.data.ChangeImageStorage;
import com.mashazavolnyuk.effectstudio.filter.Filter;

import java.util.List;

/**
 * Created by Dark Maleficent on 12.08.2016.
 */
public class FiltersListAdapter extends RecyclerView.Adapter<FiltersListHolders> {

    private Context context;
    private List<Filter> data;

    public FiltersListAdapter(Context context) {
        this.context = context;
        data = ChangeImageStorage.getInstance().getFilters();

    }


    @Override
    public FiltersListHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.item_list_effect_tools, null);
        FiltersListHolders rcv = new FiltersListHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(FiltersListHolders holder, int position) {
        holder.description.setText(data.get(position).getName());
       holder.imageChange=data.get(position).getClass().getName();
        holder.effect.setImageBitmap(data.get(position).getPreview());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
