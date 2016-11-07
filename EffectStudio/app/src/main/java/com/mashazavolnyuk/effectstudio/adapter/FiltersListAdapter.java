package com.mashazavolnyuk.effectstudio.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.data.ChangeImageStorage;
import com.mashazavolnyuk.effectstudio.filter.Filter;
import com.mashazavolnyuk.effectstudio.interfaces.IObservableRecyclerTools;
import com.mashazavolnyuk.effectstudio.interfaces.IObserveRecyclerTools;

import java.util.List;

/**
 * Created by Dark Maleficent on 12.08.2016.
 */
public class FiltersListAdapter extends RecyclerView.Adapter<FiltersListHolders> implements IObservableRecyclerTools {

    private Context context;
    private List<Filter> data;
    private IObserveRecyclerTools observer;

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
        holder.imageChange = data.get(position).getClass().getName();
        holder.effect.setImageBitmap(data.get(position).getPreview());
        holder.position = position;
        holder.observer=this.observer;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void setObserver(IObserveRecyclerTools observer) {
        this.observer=observer;
    }
}
