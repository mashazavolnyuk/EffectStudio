package com.example.darkmaleficent.effectstudio.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.interfaces.IRegulator;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public class FragmentRegulatorProperty extends Fragment implements IRegulator{
    int Max=0;
    int Min=0;
    SeekBar seekBarRugelator;
  //  ImageView imgDone;
    ImageView imgPreview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_regulator, null);
        seekBarRugelator=(SeekBar) v.findViewById(R.id.seekBarRegulator);
    //    imgDone =(ImageView) v.findViewById(R.id.imgDone);
        imgPreview=(ImageView)v.findViewById(R.id.imgPreview);
        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        MenuInflater menuInflater = (getActivity()).getMenuInflater();
        menuInflater.inflate(R.menu.choose_filters,menu);
        for (int j = 0; j < menu.size(); j++) {
            MenuItem item = menu.getItem(j);
            item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.checkDone:
                Toast.makeText(getActivity(),"qwwr",Toast.LENGTH_SHORT).show();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setMaxMin(int Max, int Min) {
        this.Max=Max;
        this.Min=Min;
        seekBarRugelator.setProgress(20);
        seekBarRugelator.setMax(Max);
    }

}
