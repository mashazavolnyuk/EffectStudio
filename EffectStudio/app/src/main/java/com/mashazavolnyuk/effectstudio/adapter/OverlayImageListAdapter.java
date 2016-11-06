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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dark Maleficent on 19.10.2016.
 */

public class OverlayImageListAdapter extends RecyclerView.Adapter<OverlayImageListHolders> {

    private Context context;
    private List<CardImage> data=new ArrayList<>();


    public OverlayImageListAdapter(Context context) {
        this.context = context;
        data = CardStorage.getInstance().getCardImageList();
    }

    @Override
    public OverlayImageListHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.item_cover_stickers, null);
        OverlayImageListHolders rcv = new OverlayImageListHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(OverlayImageListHolders holder, int position) {
        holder.textCover.setText(data.get(position).getName());
        Picasso.with(context).load(Uri.parse(data.get(position).getImgUrl())).into(holder.imgCover);
        holder.urlStickers=data.get(position).getImageUrls();
        holder.c=context;

    }

    @Override
    public int getItemCount() {
        Log.d("size stickers", "count" + data.size());
        return data.size();
    }

    public void update(){
        data = CardStorage.getInstance().getCardImageList();
        notifyDataSetChanged();
    }
}