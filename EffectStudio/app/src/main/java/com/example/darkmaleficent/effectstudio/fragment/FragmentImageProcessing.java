package com.example.darkmaleficent.effectstudio.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.adapter.EffectsListAdapter;
import com.example.darkmaleficent.effectstudio.adapter.PropertiesAdapter;
import com.example.darkmaleficent.effectstudio.data.ImageStorage;
import com.example.darkmaleficent.effectstudio.interfaces.IObserveRecyclerTools;
import com.example.darkmaleficent.effectstudio.interfaces.IObserveWorkingImage;
import com.example.darkmaleficent.effectstudio.interfaces.ISwitchCanvas;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

/**
 * Created by Dark Maleficent on 12.06.2016.
 */
public class FragmentImageProcessing extends Fragment implements IObserveWorkingImage, IObserveRecyclerTools {
    ImageView imageView;
    RecyclerView barToolsEffect;
    View v;
    EffectsListAdapter adapter;
    String[] SPINNERLIST = {"Effect","Filters", "Properties"};
    int positionBar = 0;
    ViewGroup group;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
//        ViewGroup mContainer = (ViewGroup) getActivity().findViewById(R.id.mainContent);
//        mContainer.removeAllViewsInLayout();
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_image_processing, null);
        setHasOptionsMenu(true);
        barToolsEffect = (RecyclerView) v.findViewById(R.id.rcvToolsEffect);
        imageView = (ImageView) v.findViewById(R.id.workingImage);
        group=(ViewGroup) v.findViewById(R.id.workingSpace);
        ImageStorage.getInstance().setObserver(this);
        setToolsBar(positionBar);
        Bitmap bitmap = ImageStorage.getInstance().getBmp();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Effect");
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            Bitmap bitmap1= BitmapFactory.decodeResource(getResources(),R.mipmap.cat);
            imageView.setImageBitmap(bitmap1);
            barToolsEffect.setEnabled(false);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        final MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                v.findViewById(R.id.material_design_spinner);
        materialDesignSpinner.setAdapter(arrayAdapter);
        materialDesignSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setToolsBar(i);
                positionBar = i;
            }
        });
        return v;
    }

    private void setToolsBar(int position) {

        switch (position) {

            case 0:
                EffectsListAdapter effectsListAdapter = new EffectsListAdapter(getActivity());
                barToolsEffect.setAdapter(effectsListAdapter);
                break;
            case 1:
                Bitmap bmp=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_add_a_photo_white_36dp);
                ((ISwitchCanvas)getActivity()).switchOnCanvas(true,bmp,group);
                break;
            case 2:
                PropertiesAdapter propertiseAdapter = new PropertiesAdapter(getActivity());
                barToolsEffect.setAdapter(propertiseAdapter);

                break;

        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //getActivity().getMenuInflater().inflate(R.menu.image_processing, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.chooser:
//                ((IChooserFilters) getActivity()).toChoosefilter();
//                break;
        //  }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void newState(boolean state) {
        if (state) {
            Bitmap bitmap = ImageStorage.getInstance().getBmp();
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
