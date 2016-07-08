package com.example.darkmaleficent.effectstudio;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Dark Maleficent on 12.06.2016.
 */
public class FragmentWorkingImage extends Fragment implements IObserveWorkingImage,IObserveRecyclerTools {
    ImageView imageView;
    RecyclerView barToolsEffect;
    View v;
    RecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_image_view, null);
        setHasOptionsMenu(true);
        barToolsEffect = (RecyclerView) v.findViewById(R.id.rcvToolsEffect);
        imageView = (ImageView) v.findViewById(R.id.workingImage);
        ImageManagerLoader.getInstance().setObserver(this);
         adapter = new RecyclerViewAdapter(getActivity());
        barToolsEffect.setAdapter(adapter);
        Bitmap bitmap = ImageManagerLoader.getInstance().getWorkingBitmap();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Effect");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (bitmap != null)
            imageView.setImageBitmap(bitmap);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.choose_filters, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chooser:
                ((IChooserFilters) getActivity()).toChoosefilter();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void newState(boolean state) {
        if (state) {
            Bitmap bitmap = ImageManagerLoader.getInstance().getWorkingBitmap();
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void updateRecycler(boolean flag) {
        if (flag) {
            adapter.notifyDataSetChanged();
        }
    }
}
