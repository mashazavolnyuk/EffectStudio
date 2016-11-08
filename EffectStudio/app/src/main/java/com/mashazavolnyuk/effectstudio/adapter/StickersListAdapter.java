package com.mashazavolnyuk.effectstudio.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mashazavolnyuk.effectstudio.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dark Maleficent on 31.10.2016.
 */

public class StickersListAdapter extends RecyclerView.Adapter<StickersListHolders> {

    Context context;
    List<String> urlsStickers=new ArrayList<>();

    public StickersListAdapter(Context context,List<String> urlsStickers) {
        this.urlsStickers=urlsStickers;
        this.context = context;

    }

    @Override
    public StickersListHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.item_image_preview, null);
        StickersListHolders rcv = new StickersListHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(StickersListHolders holder, int position) {
        Picasso.with(context).load(Uri.parse(urlsStickers.get(position))).into(holder.imageView);
        holder.context=this.context;
    }

    @Override
    public int getItemCount() {
        return urlsStickers.size();
    }
}
