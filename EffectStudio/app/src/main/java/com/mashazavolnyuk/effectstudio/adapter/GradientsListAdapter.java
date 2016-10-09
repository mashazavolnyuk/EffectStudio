package com.mashazavolnyuk.effectstudio.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.data.ChangeImageStorage;
import com.mashazavolnyuk.effectstudio.gradient.Gradient;
import com.mashazavolnyuk.effectstudio.interfaces.IObservableRecyclerTools;
import com.mashazavolnyuk.effectstudio.interfaces.IObserveRecyclerTools;

import java.util.List;

/**
 * Created by Dark Maleficent on 07.09.2016.
 */
public class GradientsListAdapter extends RecyclerView.Adapter<GradientsListHolders> implements IObservableRecyclerTools {

    private Context context;
    private List<Gradient> data;
    private IObserveRecyclerTools observer;

    public GradientsListAdapter(Context context) {
        this.context = context;
        data = ChangeImageStorage.getInstance().getGradients();

    }

    @Override
    public GradientsListHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.item_list_effect_tools, null);
        GradientsListHolders rcv = new GradientsListHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(GradientsListHolders holder, int position) {
        holder.description.setText(data.get(position).getName());
        Typeface type = Typeface.createFromAsset(context.getAssets(),"Roboto-BlackItalic.ttf");
        holder.description.setTypeface(type);
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
