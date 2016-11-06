package com.mashazavolnyuk.effectstudio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.adapter.StickersListAdapter;

import java.util.List;

/**
 * Created by Dark Maleficent on 06.11.2016.
 */

public class FragmentListStickers extends Fragment {
    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_stickers, null);
        setHasOptionsMenu(true);
        recyclerView = (RecyclerView) v.findViewById(R.id.rcvStickersList);
        Bundle bundle = this.getArguments();
        List<String> strings = bundle.getStringArrayList("listUrlStikers");
        StickersListAdapter stickersListAdapter = new StickersListAdapter(getActivity(), strings);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(stickersListAdapter);
        return v;
    }
}
