package com.example.darkmaleficent.effectstudio.fragment;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.darkmaleficent.effectstudio.data.ImageStorage;
import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.interfaces.IMenuGallery;


public class DialogMenuImageFromGallery extends DialogFragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog_menu_image_from_gallery, null);
        v.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textShare:
                onShareItem();
                break;
        }
    }
    private void onShareItem() {
        Bitmap bmp= ImageStorage.getInstance().getWorkingBitmap();
        ((IMenuGallery)getActivity()).toShare(bmp);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}
