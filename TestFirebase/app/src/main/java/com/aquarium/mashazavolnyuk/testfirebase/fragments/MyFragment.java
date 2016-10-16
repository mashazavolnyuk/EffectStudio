package com.aquarium.mashazavolnyuk.testfirebase.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aquarium.mashazavolnyuk.testfirebase.R;
import com.aquarium.mashazavolnyuk.testfirebase.adapter.StickersAdapter;
import com.aquarium.mashazavolnyuk.testfirebase.cardstickers.CardImage;
import com.aquarium.mashazavolnyuk.testfirebase.data.CardStickers;

import java.util.List;

/**
 * Created by Dark Maleficent on 06.10.2016.
 */

public class MyFragment extends Fragment {
    RecyclerView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stickers,
                container, false);
        StickersAdapter adapter=new StickersAdapter(getActivity());
        List<CardImage> cardImages= CardStickers.getInstance().getCardImageList();
        listView=(RecyclerView) view.findViewById(R.id.listCard);
        listView.setAdapter(adapter);
        return view;
    }
}
