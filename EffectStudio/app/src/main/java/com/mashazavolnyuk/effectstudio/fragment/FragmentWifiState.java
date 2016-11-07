package com.mashazavolnyuk.effectstudio.fragment;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.mashazavolnyuk.effectstudio.R;

/**
 * Created by Dark Maleficent on 07.11.2016.
 */

public class FragmentWifiState extends Fragment {

    Switch switchWifi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wifi_state, container, false);
        switchWifi = (Switch) v.findViewById(R.id.swWifi);
        switchWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    WifiManager wifi;
                    wifi = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
                    wifi.setWifiEnabled(true);//Turn on Wifi
                }
            }
        });
        return v;
    }
}
