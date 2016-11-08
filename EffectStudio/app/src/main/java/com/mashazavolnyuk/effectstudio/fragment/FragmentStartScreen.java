package com.mashazavolnyuk.effectstudio.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.interfaces.INavigation;

/**
 * Created by Dark Maleficent on 16.10.2016.
 */

public class FragmentStartScreen extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start_screen, null);
        Button takePhoto=(Button) v.findViewById(R.id.btnStartScreenPhoto);
        Button btnGallery=(Button) v.findViewById(R.id.btnStartScreenGallery);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((INavigation)getActivity()).loadImagefromCamera();

            }
        });
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((INavigation)getActivity()).loadImagefromGallery();
            }
        });
        return v;
    }
}
