package com.example.darkmaleficent.effectstudio.fragment;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.adapter.PropertiesAdapter;
import com.example.darkmaleficent.effectstudio.data.ImageStorage;
import com.example.darkmaleficent.effectstudio.interfaces.IObserveRecyclerTools;
import com.example.darkmaleficent.effectstudio.interfaces.IObserveWorkingImage;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

/**
 * Created by Dark Maleficent on 12.06.2016.
 */
public class FragmentImageProcessing extends Fragment implements IObserveWorkingImage, IObserveRecyclerTools {
    ImageView imageView;
    RecyclerView barToolsEffect;
    View v;
    PropertiesAdapter adapter;
    String[] SPINNERLIST = {"Filters", "Effect","Properties"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_image_view, null);
        setHasOptionsMenu(true);
        barToolsEffect = (RecyclerView) v.findViewById(R.id.rcvToolsEffect);
        imageView = (ImageView) v.findViewById(R.id.workingImage);
        ImageStorage.getInstance().setObserver(this);
        adapter = new PropertiesAdapter(getActivity());
        barToolsEffect.setAdapter(adapter);
        Bitmap bitmap = ImageStorage.getInstance().getWorkingBitmap();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Effect");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (bitmap != null)
            imageView.setImageBitmap(bitmap);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        final MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                v.findViewById(R.id.material_design_spinner);
        materialDesignSpinner.setAdapter(arrayAdapter);
        materialDesignSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){

                    case 0:

                        break;
                    case 2:
                        PropertiesAdapter propertiseAdapter=new PropertiesAdapter(getActivity());
                        barToolsEffect.setAdapter(propertiseAdapter);

                        break;

                }
                Toast.makeText(getActivity(), "i=" + i + ", l=" + l, Toast.LENGTH_SHORT).show();

            }
        });
        return v;
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
            Bitmap bitmap = ImageStorage.getInstance().getWorkingBitmap();
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
