package com.aquarium.mashazavolnyuk.testfirebase.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aquarium.mashazavolnyuk.testfirebase.R;
import com.aquarium.mashazavolnyuk.testfirebase.cardstickers.CardImage;
import com.aquarium.mashazavolnyuk.testfirebase.data.CardStickers;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dark Maleficent on 28.09.2016.
 */

public class StickersAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private Context context;
    private List<CardImage> data;

    public StickersAdapter(Context context) {
        this.context = context;
        data = CardStickers.getInstance().getCardImageList();

    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.item_stickers_cards, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.nameCategory.setText(data.get(position).getName());
        holder.id = data.get(position).getId();
        // holder.imgCategory.setImageURI(Uri.parse(data.get(position).getImgUrl()));
        Picasso.with(context).load(Uri.parse(data.get(position).getImgUrl())).into(holder.imgCategory);

    }

    @Override
    public int getItemCount() {
        Log.d("size stickers", "count" + data.size());
        return data.size();

    }
}
