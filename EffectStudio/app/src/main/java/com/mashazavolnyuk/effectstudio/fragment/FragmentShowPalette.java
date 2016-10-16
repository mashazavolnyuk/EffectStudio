package com.mashazavolnyuk.effectstudio.fragment;

import android.app.Fragment;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.adapter.PaletteListAdapter;
import com.mashazavolnyuk.effectstudio.data.ImageStorage;
import com.mashazavolnyuk.effectstudio.interfaces.INavigation;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by Dark Maleficent on 09.10.2016.
 */

public class FragmentShowPalette extends Fragment {
    private Bitmap mainBmp;
    ImageView img;
    RecyclerView rcvColorPallete;
    List<Integer> colors;

    @Override
    public void onDestroyView() {
//        ViewGroup mContainer = (ViewGroup) getActivity().findViewById(R.id.mainContent);
//        mContainer.removeAllViewsInLayout();
        ((INavigation)getActivity()).toModifyImage();
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pallete, null);
        setHasOptionsMenu(true);
        img = (ImageView) v.findViewById(R.id.imgPicturePalette);
        rcvColorPallete = (RecyclerView) v.findViewById(R.id.rcvColorPalette);
        fillData();
        return v;
    }

    private void fillData() {
        mainBmp = ImageStorage.getInstance().getBmpOriginal();
        img.setImageBitmap(mainBmp);
        colors = getColorsFromBitmap();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        MenuInflater menuInflater = (getActivity()).getMenuInflater();
        menuInflater.inflate(R.menu.menu_palette, menu);
        for (int j = 0; j < menu.size(); j++) {
            MenuItem item = menu.getItem(j);
            if (item.getItemId() == R.id.copyPalette)
                item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
            item.setIcon(R.mipmap.ic_content_copy_white_36dp);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.copyPalette:
                copyColorsMemory();
                Toast.makeText(getActivity(),"text has been copied",Toast.LENGTH_LONG).show();
                break;}
        return super.onOptionsItemSelected(item);
    }
    private void copyColorsMemory(){
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
        StringBuilder colorsText=new StringBuilder();
        String temp;
        for(Integer c:colors){
            int color=c;
            temp = String.format("#%06X", (0xFFFFFF & color));
            colorsText.append(temp);
            colorsText.append(" ");
        }
        ClipData clip = ClipData.newPlainText("label", colorsText);
        clipboard.setPrimaryClip(clip);
    }
}