package com.example.darkmaleficent.effectstudio.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.adapter.GalleryImageAdapter;
import com.example.darkmaleficent.effectstudio.interfaces.IListener;
import com.example.darkmaleficent.effectstudio.interfaces.INavigation;
import com.example.darkmaleficent.effectstudio.interfaces.IObserve;
import com.vk.sdk.VKScope;
import com.vk.sdk.api.model.VKApiPhotoAlbum;
import com.vk.sdk.api.model.VKList;

;

/**
 * Created by Dark Maleficent on 07.05.2016.
 */
public class FragmentMainGallery extends Fragment implements IObserve {


    private FloatingActionButton fabPlus;
    private FloatingActionButton fabLoadImageFromCamera;
    private FloatingActionButton fabLoadImageFromGallery;
    private GalleryImageAdapter galleryImageAdapter;
    private String[]scope=new String[]{VKScope.WALL,VKScope.PHOTOS};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_grid_image, null);
        GridView gridView = (GridView) v.findViewById(R.id.gridView);
        galleryImageAdapter = new GalleryImageAdapter(getActivity(), (INavigation) getActivity());
        galleryImageAdapter.setObserver(this);
        gridView.setAdapter(galleryImageAdapter);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void updateAlbums(VKList albums){
        for(Object obj : albums){
            if(obj instanceof VKApiPhotoAlbum){
                VKApiPhotoAlbum album = (VKApiPhotoAlbum)obj;


            }
        }
    }

    public IListener getGalleryImageAdapter() {
        return galleryImageAdapter;
    }


}
