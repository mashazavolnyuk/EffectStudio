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
import com.mashazavolnyuk.effectstudio.adapter.OverlayImageListAdapter;

/**
 * Created by Dark Maleficent on 31.10.2016.
 */

public class FragmentStickersOrFramesList extends Fragment {

    private RecyclerView recyclerView;
    private OverlayImageListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stickers, null);
        recyclerView = (RecyclerView) v.findViewById(R.id.rcvStickersFrames);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new OverlayImageListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        return v;
    }
}
