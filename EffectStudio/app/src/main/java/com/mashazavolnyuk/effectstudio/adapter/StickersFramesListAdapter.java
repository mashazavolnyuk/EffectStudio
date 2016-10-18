package com.mashazavolnyuk.effectstudio.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.data.CardImage;
import com.mashazavolnyuk.effectstudio.data.CardStorage;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Dark Maleficent on 19.10.2016.
 */

public class StickersFramesListAdapter extends RecyclerView.Adapter<StickersFramesListHolders> {

    private Context context;
    private List<CardImage> data;


    public StickersFramesListAdapter(Context context) {
        this.context = context;
        data = CardStorage.getInstance().getCardImageList();


    }

    @Override
    public StickersFramesListHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.item_over_sticker, null);
        StickersFramesListHolders rcv = new StickersFramesListHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(StickersFramesListHolders holder, int position) {
        holder.textCover.setText(data.get(position).getName());
        Picasso.with(context).load(Uri.parse(data.get(position).getImgUrl())).into(holder.imgCover);
        holder.urlStickers = data.get(position).getImageUrls();

    }

    @Override
    public int getItemCount() {
        Log.d("size stickers", "count" + data.size());
        return data.size();
    }
}
