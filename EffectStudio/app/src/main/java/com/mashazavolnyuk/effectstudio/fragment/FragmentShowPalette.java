package com.mashazavolnyuk.effectstudio.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.adapter.PaletteListAdapter;
import com.mashazavolnyuk.effectstudio.data.ImageStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dark Maleficent on 09.10.2016.
 */

public class FragmentShowPalette extends Fragment {
    private Bitmap mainBmp;
    ImageView img;
    RecyclerView rcvColorPallete;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pallete, null);
        img = (ImageView) v.findViewById(R.id.imgPicturePalette);
        rcvColorPallete = (RecyclerView) v.findViewById(R.id.rcvColorPalette);
        fillData();
        return v;
    }

    private void fillData() {
        Bitmap mainBmp = ImageStorage.getInstance().getBmpOriginal();
        img.setImageBitmap(mainBmp);
        List<Integer> colors = getColorsFromBitmap();
        PaletteListAdapter adapter = new PaletteListAdapter(getActivity(), colors);
        rcvColorPallete.setAdapter(adapter);
    }

    private List<Integer> getColorsFromBitmap() {
        List<Integer> colors = new ArrayList<>();
        Palette palette = Palette.from(mainBmp).generate();
        int defaultColor = 0x000000;
        colors.add(palette.getVibrantColor(defaultColor));
        colors.add(palette.getVibrantColor(defaultColor));
        colors.add(palette.getLightVibrantColor(defaultColor));
        colors.add(palette.getDarkVibrantColor(defaultColor));
        colors.add(palette.getMutedColor(defaultColor));
        colors.add(palette.getLightMutedColor(defaultColor));
        colors.add(palette.getDarkMutedColor(defaultColor));
        return colors;
    }
}
