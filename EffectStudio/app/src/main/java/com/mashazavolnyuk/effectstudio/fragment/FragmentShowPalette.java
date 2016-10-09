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

    @Override
    public void onDestroyView() {
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
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", "Text to copy");
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getActivity(),"text copied",Toast.LENGTH_LONG).show();
                break;}
        return super.onOptionsItemSelected(item);
    }
}
